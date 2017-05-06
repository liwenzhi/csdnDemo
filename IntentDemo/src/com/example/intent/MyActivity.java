package com.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MyActivity extends Activity {
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void jump(View view) {
        startActivityForResult(new Intent(this, SecondActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "requestCode:" + requestCode + ",resultCode" + resultCode);
        if (requestCode == 100 & resultCode != 111) {
            finish();
        }
    }
}
