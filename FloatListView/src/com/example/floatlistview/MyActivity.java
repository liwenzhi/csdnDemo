package com.example.floatlistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MyActivity extends Activity {
    ListView lv;  //listView控件
    ArrayList<String> list = new ArrayList<String>();//数据源
    ArrayAdapter adapter; //适配器

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv = (ListView) findViewById(R.id.lv);

        for (int i = 0; i < 100; i++) {  //设置数据
            list.add("第  " + (i + 1) + "  行");
        }
        adapter = new ArrayAdapter(this, android.R.layout.activity_list_item, android.R.id.text1, list); //实例化适配器对象

        lv.setAdapter(adapter); //给ListView设置适配器

        //下面设置悬浮和头顶部分内容
        final View header = View.inflate(this, R.layout.see_or_not, null);//头部内容,会隐藏的部分
        lv.addHeaderView(header);//添加头部
        final View header2 = View.inflate(this, R.layout.see_along, null);//头部内容,一直显示的部分
        lv.addHeaderView(header2);//添加头部
        final LinearLayout invis = (LinearLayout) findViewById(R.id.invis);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    invis.setVisibility(View.VISIBLE);
                } else {
                    invis.setVisibility(View.GONE);
                }
            }
        });


    }
}
