package com.example.zipdemo;

import android.util.Log;
import com.example.zipdemo.bean.XmlBean;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 读取Zippy的工具类
 */

public class ZipUtil {


    // 创建一个List集合存放从XML文件获取到的数据
    List<XmlBean> list = null;
    XmlBean xmlBean = null;

    //扫描压缩文件里面的文件名字
    public static void scanZipFile(String zipname) {
        try {
            ZipInputStream zin = new ZipInputStream(
                    new FileInputStream(zipname));
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                //压缩文件里面的文件名称
                System.out.println(entry.getName());
                zin.closeEntry();
            }
            zin.close();
        } catch (IOException e) {
        }
    }

    //扫描压缩文件里面的某一个文件的数据
    public List<XmlBean> scanZipFile(String zipname, String name) {
        Log.e("TAG", "scanZipFile");
        try {
            ZipInputStream zin = new ZipInputStream(
                    new FileInputStream(zipname));
            ZipEntry entry;
            Log.e("TAG", "entry");
            while ((entry = zin.getNextEntry()) != null) {
                if (entry.getName().equals(name)) {

//                    InputStream is = new ZipInputStream(zin);
//                    StringBuffer stringBuffer = new StringBuffer();
//
//                    int len = 0;
//                    byte[] buf = new byte[1024];
//                    while ((len = is.read(buf)) != -1) {
//                        stringBuffer.append(new String(buf, 0, len));
//                    }
//                    Log.e("TAG", "stringBuffer:" + stringBuffer);


                    // 取得SAXParserFactory实例
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    // 从factory获取SAXParser实例
                    SAXParser parser = null;
                    try {
                        parser = factory.newSAXParser();

                        // 实例化定义的Handler
                        Myhandler handler = new Myhandler();
                        // 根据Handler规则解析输入流

                        parser.parse(zin, handler);
                        // 打印信息
                        handler.showMessage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //返回集合对象
                    return list;


                }
                zin.closeEntry();
            }
            zin.close();
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
        }
        return null;
    }


    //实现SAX方法解析数据必须要借助DefaultHandler 类的实现
    class Myhandler extends DefaultHandler {

        StringBuffer sb = null;

        // 根标签开始时
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            Log.e("TAG", "startDocument");
            list = new ArrayList<XmlBean>();
            sb = new StringBuffer();
        }

        // 节点开始时
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            Log.e("TAG", "startElement");
            // 当遍历到Car节点就新建Car对象
            if (localName.equals("data")) {
                xmlBean = new XmlBean();
//                car.id = attributes.getValue(0);// 获取第一个属性值,节点值在后面获取
            }
            // 将字符长度设置为零，以便从新开始读取元素内就字符节点
            sb.setLength(0);
        }

        // 读取字符流
        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            super.characters(ch, start, length);

            sb.append(ch, start, length);
            Log.e("TAG", "sb" + sb.toString());

        }

        // 节点结束时
        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            super.endElement(uri, localName, qName);
            Log.e("TAG", "localName:" + localName);
            //判断节点并给对象赋值
            if (localName.equals("startTime")) {
                xmlBean.setStartTime(sb.toString());
            } else if (localName.equals("maxPress")) {
                xmlBean.setMaxPress(Integer.parseInt(sb.toString()));
            } else if (localName.equals("minPress")) {
                xmlBean.setMinPress(Integer.parseInt(sb.toString()));
            } else if (localName.equals("heartRate")) {
                xmlBean.setHeartRate(Integer.parseInt(sb.toString()));
            } else if (localName.equals("pulseRate")) {
                xmlBean.setPulseRate(Integer.parseInt(sb.toString()));
            } else if (localName.equals("data")) {
                list.add(xmlBean);
            }


        }

        public void showMessage() {
            // 打印获得的数据
            for (XmlBean c : list) {
                Log.e("TAGc", c.toString());
            }

        }

    }


    //上面是SAX方法解析数据的过程


    /*
     * 这个是解压ZIP格式文件的方法
     *
     * @zipFileName：是传进来你要解压的文件路径，包括文件的名字；如：/mnt/shared/xmldata.zip
     *
     * @outputDirectory:选择你要保存的路劲； 如： /mnt/shared/
     *
     */
    public static void unzip(String zipFileName, String outputDirectory) {
        try {

            ZipInputStream in = new ZipInputStream(new FileInputStream(zipFileName));

            Log.e("TAG", "in==null" + (in == null));

            ZipEntry z;
            String name = "";
            String extractedFile = "";
            int counter = 0;
            Log.e("TAG", "unzipping file: ");
            while ((z = in.getNextEntry()) != null) {
                name = z.getName();
                Log.e("TAG", "unzipping file: " + name);
                if (z.isDirectory()) {
                    Log.e("TAG", name + "is a folder");
                    // get the folder name of the widget
                    name = name.substring(0, name.length() - 1);
                    File folder = new File(outputDirectory + File.separator + name);
                    folder.mkdirs();
                    if (counter == 0) {
                        extractedFile = folder.toString();
                    }
                    counter++;
                    Log.e("TAG", "mkdir " + outputDirectory + File.separator + name);
                } else {
                    Log.e("TAG", name + "is a normal file");
                    File file = new File(outputDirectory + File.separator + name);
                    file.createNewFile();
                    // get the output stream of the file
                    FileOutputStream out = new FileOutputStream(file);
                    int ch;
                    byte[] buffer = new byte[1024];
                    // read (ch) bytes into buffer
                    while ((ch = in.read(buffer)) != -1) {
                        // write (ch) byte from buffer at the position 0
                        out.write(buffer, 0, ch);
                        out.flush();
                    }
                    out.close();
                }
            }

            in.close();
        } catch (Exception e) {
            Log.e("TAG", "getMessage():" + e.getMessage());
        }
    }


    /**
     * 解压缩一个文件
     *
     * @param zipFile    压缩文件
     * @param folderPath 解压缩的目标目录
     * @throws IOException 当解压缩过程出错时抛出
     */

    public static void upZipFile(File zipFile, String folderPath)
            throws ZipException, IOException {
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        ZipFile zf = new ZipFile(zipFile);
        for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            InputStream in = zf.getInputStream(entry);
            String str = folderPath;
            // str = new String(str.getBytes("8859_1"), "GB2312");
            File desFile = new File(str, java.net.URLEncoder.encode(
                    entry.getName(), "UTF-8"));

            if (!desFile.exists()) {
                File fileParentDir = desFile.getParentFile();
                if (!fileParentDir.exists()) {
                    fileParentDir.mkdirs();
                }
            }

            OutputStream out = new FileOutputStream(desFile);
            byte buffer[] = new byte[1024 * 1024];
            int realLength = in.read(buffer);
            while (realLength != -1) {
                out.write(buffer, 0, realLength);
                realLength = in.read(buffer);
            }

            out.close();
            in.close();

        }
    }

    /**
     * 2     * 解压缩功能.
     * 3     * 将zipFile文件解压到folderPath目录下.
     * 4     * @throws Exception
     * 5
     */
    public static boolean upZipFile2(File zipFile, String folderPath) throws ZipException, IOException {
        //public static void upZipFile() throws Exception{
        ZipFile zfile = new ZipFile(zipFile);
        Enumeration zList = zfile.entries();
        ZipEntry ze = null;
        byte[] buf = new byte[1024];
        while (zList.hasMoreElements()) {
            ze = (ZipEntry) zList.nextElement();
            if (ze.isDirectory()) {
                Log.e("upZipFile", "ze.getName() = " + ze.getName());
                String dirstr = folderPath + ze.getName();
                //dirstr.trim();
                dirstr = new String(dirstr.getBytes("8859_1"), "GB2312");
                Log.e("upZipFile", "str = " + dirstr);
                File f = new File(dirstr);
                f.mkdir();
                continue;
            }
            Log.e("upZipFile", "ze.getName() = " + ze.getName());
            OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
            InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
            int readLen = 0;
            while ((readLen = is.read(buf, 0, 1024)) != -1) {
                os.write(buf, 0, readLen);
                Log.e("upZipFile", "writing");
            }
            is.close();
            os.close();
        }
        zfile.close();
        Log.e("upZipFile", "finish");
        return true;
    }

    public static File getRealFileName(String baseDir, String absFileName) {
        String[] dirs = absFileName.split("/");
        String lastDir = baseDir;
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                lastDir += (dirs[i] + "/");
                File dir = new File(lastDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                    Log.e("getRealFileName", "create dir = " + (lastDir + "/" + dirs[i]));
                }
            }
            File ret = new File(lastDir, dirs[dirs.length - 1]);
            Log.e("upZipFile", "2ret = " + ret);
            return ret;
        } else {
            return new File(baseDir, absFileName);
        }

    }

}


