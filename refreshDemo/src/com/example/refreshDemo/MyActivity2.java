package com.example.refreshDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 只有下拉刷新功能的Demo
 */
public class MyActivity2 extends Activity implements SwipeRefreshLayout.OnRefreshListener, RefreshLayout.OnLoadListener {

    private RefreshLayout swipeLayout;   //下拉刷新

    private ListView listView; //ListView

    private ListViewAdapter adapter; // ListView适配器

    private List<String> infoList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        swipeLayout = (RefreshLayout) this.findViewById(R.id.swipe_refresh);
        swipeLayout.setOnRefreshListener(this); //监听下拉加载
        swipeLayout.setOnLoadListener(this);//监听下拉刷新

        // 顶部刷新的样式
        swipeLayout.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);

        listView = (ListView) this.findViewById(R.id.listview);
        adapter = new ListViewAdapter(this, infoList);
        listView.setAdapter(adapter);

    }

    int index = 1;

    /**
     * 下拉刷新后回调的方法
     */
    public void onRefresh() {
        //延迟一秒钟再执行任务
        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeLayout.setRefreshing(false);     //取消刷新状态
                infoList.add(0, index + "上拉刷新");
                index++;
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }


    @Override
    public void onLoad() {
        //延迟一秒钟再执行任务
        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeLayout.setLoading(false);      //取消下拉加载状态
                infoList.add(index + "下拉加载");
                index++;
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}
