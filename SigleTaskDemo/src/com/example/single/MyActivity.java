package com.example.single;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 第一个页面
 */
public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.e("TAG", "MyActivity----onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "MyActivity----onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "MyActivity----onDestroy");
    }

    public void jump(View view) {
        startActivity(new Intent(this, OtherActivity.class));
    }
    public void close(View view) {
       finish();
    }



}
