package com.example.directory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 二级目录简单示例
 *
 *
 */
public class MyActivity extends Activity {

    ListView lv_left;
    ListView lv_right;
    MenuAdapter adapterleft;
    ChildAdapter adapterRight;
    List<MenuBean> list = new ArrayList<MenuBean>();
    List<String> listChildName = new ArrayList<String>();
    List<String> listMenuName = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        lv_left = (ListView) findViewById(R.id.lv_left);
        lv_right = (ListView) findViewById(R.id.lv_right);

    }

    private void initData() {
        listMenuName.clear();
        listChildName.clear();
        list.clear();
        //数据
        listMenuName.add("水果");
        listMenuName.add("蔬菜");
        listMenuName.add("甜点");
        listMenuName.add("汤");
        List<List<String>> listChild = new ArrayList<List<String>>();
        //水果
        List<String> list1 = new ArrayList<String>();
        list1.add("苹果");
        list1.add("香蕉");
        list1.add("雪梨");
        //蔬菜
        List<String> list2 = new ArrayList<String>();
        list2.add("大白菜");
        list2.add("黄瓜");
        list2.add("木耳");
        list2.add("番薯");
        //甜点
        List<String> list3 = new ArrayList<String>();
        list3.add("冰淇淋");
        list3.add("果冻");
        list3.add("木果奶");
        //汤
        List<String> list4 = new ArrayList<String>();
        list4.add("排骨汤");
        list4.add("西红柿蛋汤");
        list4.add("紫菜蛋汤");
        list4.add("老母鸡汤");

        listChild.add(list1);
        listChild.add(list2);
        listChild.add(list3);
        listChild.add(list4);
        for (int i = 0; i < listMenuName.size(); i++) {
            list.add(new MenuBean(listMenuName.get(i), listChild.get(i)));
        }

        //左边条目
        adapterleft = new MenuAdapter(this, listMenuName);
        adapterleft.setSelectedPosition(0);//选中第一个
        lv_left.setAdapter(adapterleft);

        //右边条目
        listChildName.addAll(list.get(0).getName());
        adapterRight = new ChildAdapter(this, listChildName);
        lv_right.setAdapter(adapterRight);

    }


    int posttionMenu = 0;

    private void initEvent() {
        //左边ListView     的条目点击事件 ，在点击左边条目的时候刷新右边ListView的数据
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listChildName.clear();
                listChildName.addAll(list.get(position).getName());
                adapterRight.notifyDataSetChanged();//刷新右边的适配器
                adapterleft.setSelectedPosition(position);
                adapterleft.notifyDataSetChanged();//刷新左边的适配器
                posttionMenu = position;
            }
        });


        //右边的条目的点击事件监听
        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //清除数据
                listChildName.clear();
                listMenuName.clear();
                adapterleft.notifyDataSetChanged();
                adapterRight.notifyDataSetChanged();
                //获取选中的数据
                Toast.makeText(MyActivity.this, list.get(posttionMenu).getMenuName() + "::" + list.get(posttionMenu).getName().get(position), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void again(View view) {
        initData();
    }


}
