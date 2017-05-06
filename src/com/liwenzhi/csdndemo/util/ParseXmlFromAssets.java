package com.liwenzhi.csdndemo.util;

import android.content.Context;
import com.liwenzhi.csdndemo.bean.XmlBean;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析xml文件的类，这里使用sax解析数据
 */
public class ParseXmlFromAssets {
    Context context;  //上下文
    String xmlName;   //assets文件的名字
    // 要解析文件的输入流
    InputStream is;
    // 创建一个List集合存放从XML文件获取到的数据
    List<XmlBean> list = null;
    XmlBean xmlBean = null;

    //定义构造方法传人上下文
    public ParseXmlFromAssets(Context context, String xmlName) {
        this.context = context;
        this.xmlName = xmlName;
    }

    // 使用SAX方法解析的结果  ,并返回集合对象
    public List<XmlBean> saxXmlToList() {
        try {
            // 从assets里面获取文件，
            // 通过getAssets获取到的是读取流
            is = context.getAssets().open(xmlName);
            // 取得SAXParserFactory实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // 从factory获取SAXParser实例
            SAXParser parser = factory.newSAXParser();
            // 实例化定义的Handler
            Myhandler handler = new Myhandler();
            // 根据Handler规则解析输入流
            parser.parse(is, handler);
            // 打印信息
            handler.showMessage();

            //返回集合对象
            return list;

        } catch (Exception e) {
            e.printStackTrace();
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
            list = new ArrayList<XmlBean>();
            sb = new StringBuffer();
        }

        // 节点开始时
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
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

        }

        // 节点结束时
        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            super.endElement(uri, localName, qName);

            //判断节点并给对象赋值
            if (localName.equals("time")) {
                xmlBean.setStartTime(sb.toString());
            } else if (localName.equals("maxPrice")) {
                xmlBean.setMaxPress(Integer.parseInt(sb.toString()));
            } else if (localName.equals("minPrice")) {
                xmlBean.setMinPress(Integer.parseInt(sb.toString()));
            } else if (localName.equals("sellNumber")) {
                xmlBean.setHeartRate(Integer.parseInt(sb.toString()));
            } else if (localName.equals("keepNumber")) {
                xmlBean.setPulseRate(Integer.parseInt(sb.toString()));
            } else if (localName.equals("data")) {
                list.add(xmlBean);
            }


        }

        public void showMessage() {
            // 打印获得的数据
            for (XmlBean c : list) {
                //  Log.e("SAX", c.toString());
            }

        }

    }


    //上面是SAX方法解析数据的过程

}
