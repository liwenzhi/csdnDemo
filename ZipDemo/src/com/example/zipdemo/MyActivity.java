package com.example.zipdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class MyActivity extends Activity {


    /**
     * Zip文件读取
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                Log.e("TAG", "解压中" + time);
                //解压文件
                //ZipUtil.unzip("/mnt/sdcard/helloworld.zip", "/mnt/sdcard/");
                // ZipUtil.unzip("/mnt/sdcard/ww.zip", "/mnt/sdcard/");

                //  ZipUtil.upZipFile(new File("/mnt/sdcard/IDEA.zip"), "/mnt/sdcard/");
                try {
                    ZipUtil.upZipFile2(new File("/mnt/sdcard/zsfile/IDEA.zip"), "/mnt/sdcard/zsfile/");
                } catch (IOException e) {
                    Log.e("TAG", "解压中" + e.getMessage());
                }

                Log.e("TAG", "解压完成" + ((System.currentTimeMillis() - time) + "::" + (System.currentTimeMillis() - time) / 1000 + "秒钟"));
            }
        }).start();


    }


}
