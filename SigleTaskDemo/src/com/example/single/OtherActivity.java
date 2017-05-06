package com.example.single;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 第二个页面
 */
public class OtherActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        Log.e("TAG", "OtherActivity---onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "OtherActivity---onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "OtherActivity---onDestroy");
    }

    public void jump(View view) {
        startActivity(new Intent(this, MyActivity.class));
    }

    public void close(View view) {
        finish();

    }
}
