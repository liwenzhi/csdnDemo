package com.example.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends FragmentActivity {
    ViewPager viewPager;

    List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        viewPager = (ViewPager) findViewById(R.id.home_vp);
        list.add(new Fragmemt1());
        list.add(new Fragmemt2());

//        Log.e("aa", "aa" + (2 / 0));
        //创建适配器,设置的碎片管理器使用的是getChildFragmentManager()
        Myadapter adapter = new Myadapter(getSupportFragmentManager());

        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);

    }

    //创建ViewPager适配器的类
    class Myadapter extends FragmentStatePagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
