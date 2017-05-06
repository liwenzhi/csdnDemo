package com.example.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: wanpu_oyf
 * Date: 17-4-17
 * Time: 下午3:49
 * To change this template use File | Settings | File Templates.
 */
public class Fragmemt2 extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView=new TextView(getActivity());
        textView.setText("这是第2个碎片页面");
        return textView;
    }
}
