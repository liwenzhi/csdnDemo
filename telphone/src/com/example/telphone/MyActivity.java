package com.example.telphone;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MyActivity extends Activity {
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gb);
        et =(EditText) findViewById(R.id.et);
    }

    public void click(View view){

        /**参数解释：
         *
         * 1：指定文件
         *
         * 2：文件权限
         * */												//私有
        SharedPreferences sp =getSharedPreferences("ip", MODE_PRIVATE);
        GbJieShouZhe.Log("ip传入");

        /**参数解释:
         *
         * 1:指定的文件
         *
         * 2：输入框的内容
         */
        sp.edit().putString("ipNumber",et.getText().toString()).commit();



        GbJieShouZhe.Log("拨打电话");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /***SharedPreferences:
     * SharedPreferences是Android平台上一个轻量级的存储类，
     * 主要是保存一些常用的配置比如窗口状态，
     * 一般在Activity中重载窗口状态onSaveInstanceState保存一般使用SharedPreferences完成，
     * 它提供了Android平台常规的Long长整形、Int整形、String字符串型的保存，
     * ************************************************************************************
     * 它是什么样的处理方式呢?
     SharedPreferences类似过去Windows系统上的ini配置文件，但是它分为多种权限，
     可以全局共享访问，android123提示最终是以xml方式来保存，整体效率来看不是特别的高，
     对于常规的轻量级而言比SQLite要好不少，如果真的存储量不大可以考虑自己定义文件格式。
     xml处理时Dalvik会通过自带底层的本地XML Parser解析，比如XMLpull方式，
     这样对于内存资源占用比较好。
     */



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
