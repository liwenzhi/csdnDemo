package com.example.refresh;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * SwipeRefreshListView的使用示例
 */
public class MyActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

       SwipeRefreshLayout swipe_menulayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
          initView();
    }

    private void initView() {
        swipe_menulayout= (SwipeRefreshLayout) findViewById(R.id.swipe_menulayout);
        swipe_menulayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {


    }
}
