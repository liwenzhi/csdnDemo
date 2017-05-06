package com.liwenzhi.csdndemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.liwenzhi.csdndemo.adapter.DataAdapter;
import com.liwenzhi.csdndemo.bean.XmlBean;
import com.liwenzhi.csdndemo.util.ParseXmlFromFile;
import com.liwenzhi.csdndemo.util.sort.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyActivity extends Activity implements View.OnClickListener {

    // 创建一个List集合存放从XML文件获取到的数据
    List<XmlBean> list = null;
    List<XmlBean> orderlist = new ArrayList<XmlBean>();    //排序的集合分开，否则会影响后面的图表的显示
    // 创建一个List集合存放从被隐藏的日期
    ArrayList<String> listHiddenItem = new ArrayList<String>();

    // 创建一个数组,设置这个数据的排序，0表示不被选中，1表示升序（默认），2表示降序
    int[] listButtonState = {0, 0, 0, 0, 0};

    //定义布局内的控件
    RadioGroup radioGroup;
    RadioButton main_rb_time;
    RadioButton main_rb_maxPress;
    RadioButton main_rb_minPress;
    RadioButton main_rb_heartRate;
    RadioButton main_rb_pulseRate;
    ListView listView;

    //排序的图标
    Drawable picture_up;
    Drawable picture_down;
    DataAdapter adapter;

    /**
     * ListView排序设计
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        //实例化数据
        radioGroup = (RadioGroup) findViewById(R.id.main_rg);
        main_rb_time = (RadioButton) findViewById(R.id.main_rb_time);
        main_rb_maxPress = (RadioButton) findViewById(R.id.main_rb_maxPress);
        main_rb_minPress = (RadioButton) findViewById(R.id.main_rb_minPress);
        main_rb_heartRate = (RadioButton) findViewById(R.id.main_rb_heartRate);
        main_rb_pulseRate = (RadioButton) findViewById(R.id.main_rb_pulseRate);
        //给按钮设置监听事件
        main_rb_time.setOnClickListener(this);
        main_rb_maxPress.setOnClickListener(this);
        main_rb_minPress.setOnClickListener(this);
        main_rb_heartRate.setOnClickListener(this);
        main_rb_pulseRate.setOnClickListener(this);
        picture_up = getResources().getDrawable(R.drawable.up);
        picture_down = getResources().getDrawable(R.drawable.down1);
        picture_down.setBounds(0, 0, picture_down.getMinimumWidth(), picture_down.getMinimumHeight());
        picture_up.setBounds(0, 0, picture_up.getMinimumWidth(), picture_up.getMinimumHeight());
        //给GroupButton设置监听事件
        //  radioGroup.setOnCheckedChangeListener(this);
        //解压文件
//        try {
//            long time=System.currentTimeMillis();
//            Log.e("TAG","time"+time);
//            //传人压缩文件的路径，和解压到目的的地址 ，解压后得到data.xml文件
//            ZipUtil.unzip("/mnt/sdcard/data.zip", "/mnt/sdcard/");
//            Log.e("TAG2","timeLong"+(System.currentTimeMillis()-time));
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }

        //创建解析对象
        //  ParseXmlFromAssets parseXml = new ParseXmlFromAssets(this, "data.xml");
        ParseXmlFromFile parseXml = new ParseXmlFromFile();
        //解析数据，并返回结果
        list = parseXml.saxXmlToList("/mnt/shared/Other/xmldata.zip", "xmldata.xml");
        // list = parseXml.saxXmlToList();
        //   MyApplication.setList(list);  //保存集合
        orderlist.clear();
        Log.e("TAG", "list==null" + (list == null));
        if (list != null)
            orderlist.addAll(list);
        //创建Adapter，使用BaseAdapter
        adapter = new DataAdapter(this, orderlist, listHiddenItem);
        listView.setAdapter(adapter);

        //设置删除的监听
        adapter.setiDeleteData(new DataAdapter.IDeleteData() {
            public void deleteData(int position) {
                //  list.remove(position);//删除条目数据
                if (listHiddenItem.contains(list.get(position).getStartTime())) {     //如果包含这个日期，就移除
                    listHiddenItem.remove(list.get(position).getStartTime()); //保证移除的是对象，而不是顺序的序列号
                } else {
                    listHiddenItem.add(list.get(position).getStartTime());//添加隐藏的日期
                }
                // Log.e("TAG", "listHiddenItem" + listHiddenItem);
                adapter.notifyDataSetChanged(); //刷新适配器

            }
        });

    }


    //跳转到图形界面
    public void jumpToView(View view) {
//        Intent intent = new Intent(this, CharViewActivity.class);
//        intent.putIntegerArrayListExtra("listHiddenItem", listHiddenItem);
//        startActivity(intent);
        Toast.makeText(this, "图表数据~~要排除了禁用的数据哦", Toast.LENGTH_SHORT).show();


    }


    // RadioGroup中的Button的监听方法
    @Override
    public void onClick(View v) {


        int index = 0;//点击的是第几个按钮
        switch (v.getId()) {
            case R.id.main_rb_time:
                index = 0;
                break;
            case R.id.main_rb_maxPress:
                index = 1;
                break;
            case R.id.main_rb_minPress:
                index = 2;
                break;
            case R.id.main_rb_heartRate:
                index = 3;
                break;
            case R.id.main_rb_pulseRate:
                index = 4;
                break;
        }
        //设置各个状态
        setButtonState(index);
        //根据各个按钮的状态设置图片
        //先清除所有的图标
        main_rb_time.setCompoundDrawables(null, null, null, null);
        main_rb_maxPress.setCompoundDrawables(null, null, null, null);
        main_rb_minPress.setCompoundDrawables(null, null, null, null);
        main_rb_heartRate.setCompoundDrawables(null, null, null, null);
        main_rb_pulseRate.setCompoundDrawables(null, null, null, null);

        for (int i = 0; i < listButtonState.length; i++) {
            int state = listButtonState[i];
            if (state != 0) {
                switch (i) {
                    case 0:         //第一个按钮。。。
                        setRadioButtonPicture(i, state, main_rb_time);
                        break;
                    case 1:
                        setRadioButtonPicture(i, state, main_rb_maxPress);
                        break;
                    case 2:
                        setRadioButtonPicture(i, state, main_rb_minPress);
                        break;
                    case 3:
                        setRadioButtonPicture(i, state, main_rb_heartRate);
                        break;
                    case 4:
                        setRadioButtonPicture(i, state, main_rb_pulseRate);
                        break;
                }
            }

        }

    }

    /**
     * 根据状态设置图片
     *
     * @param state
     * @param main_rb_time
     */
    private void setRadioButtonPicture(int index, int state, RadioButton main_rb_time) {
        if (state == 1) {      //第一种状态  ,升序，从小到大的排列
            main_rb_time.setCompoundDrawables(null, null, picture_up, null);
            switch (index) {
                case 0:   //时间
                    sortTimeSmallToBig();
                    break;
                case 1:   //收缩压
                    sortMaxPressSmallToBig();
                    break;
                case 2:   //舒张压
                    sortMinPressSmallToBig();
                    break;
                case 3:   //心率
                    sortHeartRateSmallToBig();
                    break;
                case 4:   //脉压率
                    sortPulseSmallToBig();
                    break;
            }

        } else {        //第二种状态    ,降序，从大到小的排列
            main_rb_time.setCompoundDrawables(null, null, picture_down, null);
            switch (index) {
                case 0:   //时间
                    sortTimeBigToSmall();
                    break;
                case 1:   //收缩压
                    sortMaxPressBigToSmall();
                    break;
                case 2:   //舒张压
                    sortMinPressBigToSmall();
                    break;
                case 3:   //心率
                    sortHeartRateBigToSmall();
                    break;
                case 4:   //脉压率
                    sortPulseBigToSmall();
                    break;
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 从小到大排序时间
     */
    private void sortTimeSmallToBig() {
        Collections.sort(orderlist, new TimeFromSmallToBig());

    }

    /**
     * 从大到小排序时间
     */
    private void sortTimeBigToSmall() {
        Collections.sort(orderlist, new TimeFromBigToSmall());

    }

    /**
     * 从小到大排序收缩压
     */
    private void sortMaxPressSmallToBig() {
        Collections.sort(orderlist, new MaxPressFromSmallToBig());
    }

    /**
     * 从大到小排序收缩压
     */
    private void sortMaxPressBigToSmall() {
        Collections.sort(orderlist, new MaxPressFromBigToSmall());
    }

    /**
     * 从小到大排序舒张压
     */
    private void sortMinPressSmallToBig() {
        Collections.sort(orderlist, new MinPressFromSmallToBig());
    }

    /**
     * 从大到小排序舒张压
     */
    private void sortMinPressBigToSmall() {
        Collections.sort(orderlist, new MinPressFromBigToSmall());
    }

    /**
     * 从小到大排序心率
     */
    private void sortHeartRateSmallToBig() {
        Collections.sort(orderlist, new HeartRateFromSmallToBig());
    }

    /**
     * 从大到小排序心率
     */
    private void sortHeartRateBigToSmall() {
        Collections.sort(orderlist, new HeartRateFromBigToSmall());
    }

    /**
     * 从小到大排序脉搏
     */
    private void sortPulseSmallToBig() {
        Collections.sort(orderlist, new PulseRateFromSmallToBig());
    }

    /**
     * 从大到小排序脉搏
     */
    private void sortPulseBigToSmall() {
        Collections.sort(orderlist, new PulseRateFromBigToSmall());
    }


    /**
     * 设置按钮的状态
     *
     * @param index
     */
    private void setButtonState(int index) {


        //如果之前不是被选中的话
        if (listButtonState[index] == 0) {
            for (int i = 0; i < listButtonState.length; i++) {
                listButtonState[i] = 0;//数字置零
            }
            listButtonState[index] = 1;     //变为1

        } else if (listButtonState[index] == 1) {//如果已经被选中了  ,转换状态
            listButtonState[index] = 2;
        } else if (listButtonState[index] == 2) {//如果已经被选中了  ,转换状态
            listButtonState[index] = 1;
        }

//        Log.e("TAG", "setButtonState" + listButtonState[0] + listButtonState[1] + listButtonState[2] + listButtonState[3] + listButtonState[4]);
    }

    public void selectShow(View view) {
        //如果隐藏列表没有数据，就不显示对话框
        if (listHiddenItem.size() == 0) {
            return;
        }
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //View dialogView = View.inflate(this, R.layout.dialog_canclehidden, null);

        // 设置提示框的标题
        builder.
                setTitle("温馨提示").
                // 设置提示框的图标
                        setIcon(R.drawable.cancle2).
                // 设置要显示的信息
                        setMessage("是否启用全部数据    ").
                //  setView(dialogView)
                        // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //清除选中的项目序列号
                        listHiddenItem.clear();
                        adapter.notifyDataSetChanged();
                    }
                }).

                // 设置取消按钮,null是什么都不做
                        setNegativeButton("取消", null)
        ;
        // 生产对话框
        AlertDialog alertDialog = builder.create();

        // 显示对话框
        alertDialog.show();

    }


}
