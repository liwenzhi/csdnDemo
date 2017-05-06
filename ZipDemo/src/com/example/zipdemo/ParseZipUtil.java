package com.example.zipdemo;

import android.util.Log;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 读取Zippy的工具类
 */

public class ParseZipUtil {


    //扫描压缩文件里面的某一个文件的数据
    public static void scanZipFile(String zipname, String name) {
        Log.e("TAG", "scanZipFile");
        try {
            ZipInputStream zin = new ZipInputStream(
                    new FileInputStream(zipname));
            ZipEntry entry;
            Log.e("TAG", "entry");
            while ((entry = zin.getNextEntry()) != null) {
                 if (entry.getName().equals(name)) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(zin));
                    String s;
                    while ((s = in.readLine()) != null)
                        Log.e("TAG", s + "\n");
                }
                zin.closeEntry();
            }
            zin.close();
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
        }
    }



}


