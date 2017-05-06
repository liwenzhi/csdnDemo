package com.example.viewPager2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 碎片页面1
 */
public class Fragment1 extends Fragment implements View.OnClickListener {


    String fragment1String;
    User fragment1User;
    TextView tv_fragment1_show;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment1String = new String("这是Fragment1页面的数据");
        fragment1User = new User("fragment1User", 88);
        View view = inflater.inflate(R.layout.fragment1, null);
        Button btn_getMyActivityData = (Button) view.findViewById(R.id.btn_getMyActivityData);
        Button btn_getFragment2Data = (Button) view.findViewById(R.id.btn_getFragment2Data);
        Button btn_changeMyActivityData = (Button) view.findViewById(R.id.btn_changeMyActivityData);
        Button btn_changeFragment2Data = (Button) view.findViewById(R.id.btn_changeFragment2Data);
        tv_fragment1_show = (TextView) view.findViewById(R.id.tv_fragment1_show);

        btn_getMyActivityData.setOnClickListener(this);
        btn_getFragment2Data.setOnClickListener(this);
        btn_changeMyActivityData.setOnClickListener(this);
        btn_changeFragment2Data.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        String msg = "";
        User user = null;

        /**
         * 获取MyActivity中的数据
         */
        if (v.getId() == R.id.btn_getMyActivityData) {
            msg = ((MyActivity) (getActivity())).myActivityString;
            user = ((MyActivity) (getActivity())).myActivityUser;
            Toast.makeText(getActivity(), msg + user, Toast.LENGTH_SHORT).show();
        }

        /**
         * 获取Fragment2中的数据
         */
        if (v.getId() == R.id.btn_getFragment2Data) {
            msg = ((Fragment2) getFragmentManager().getFragments().get(1)).fragment2String;
            user = ((Fragment2) getFragmentManager().getFragments().get(1)).fragment2User;
            Toast.makeText(getActivity(), msg + user, Toast.LENGTH_SHORT).show();
        }

        /**
         * 改变MyActivity中的数据
         */
        if (v.getId() == R.id.btn_changeMyActivityData) {
            MyActivity myActivity = (MyActivity) (getActivity());
            myActivity.tv_main_show.append("\nFragment1改变了这里的数据");
        }


        /**
         * 改变Fragment2中的数据
         */
        if (v.getId() == R.id.btn_changeFragment2Data) {
            Fragment2 fragment2 = (Fragment2) getFragmentManager().getFragments().get(1);
            fragment2.tv_fragment2_show.append("\nFragment1改变了这里的数据");
        }
    }
}
