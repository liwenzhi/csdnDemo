package com.liwenzhi.csdndemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.liwenzhi.csdndemo.R;
import com.liwenzhi.csdndemo.bean.XmlBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView的适配器,传人集合数据
 */
public class DataAdapter extends BaseAdapter {
    // 创建一个List集合存放从XML文件获取到的数据
    List<XmlBean> list = null;
    Context context;
    // 创建一个List集合存放从被隐藏的序列号
    // 创建一个List集合存放从被隐藏的日期字符串
    List<String> listHiddenItem = new ArrayList<String>();

    //构造器传人集合对象
    public DataAdapter(Context context, List<XmlBean> list, List<String> listHiddenItem) {
        this.listHiddenItem = listHiddenItem;
        this.list = list;
        this.context = context;
    }

    //返回集合的数据量
    public int getCount() {
        return list.size();
    }

    //每一条数据对于的对象
    public XmlBean getItem(int position) {
        return list.get(position);
    }

    //条码的ID
    public long getItemId(int position) {
        return position;
    }


    //每个条目的试图对象
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_data, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //给ListView的条码设置内容
        viewHolder.time.setText(list.get(position).getStartTime());
        viewHolder.maxPress.setText("" + list.get(position).getMaxPress());
        viewHolder.minPress.setText("" + list.get(position).getMinPress());
        viewHolder.heartRate.setText("" + list.get(position).getHeartRate());
        viewHolder.pulseRate.setText("" + list.get(position).getPulseRate());

        //设置按钮上的文字的显示，如果这个时间值已经被选中就显示“启用”
        if (listHiddenItem.contains(list.get(position).getStartTime())) {
            viewHolder.btn_delete.setText("启用");
            //viewHolder.btn_delete.setTextColor(Color.DKGRAY);
            viewHolder.list_item.setBackgroundColor(Color.rgb(192, 192, 192));
        } else {
            viewHolder.btn_delete.setText("禁用");
            viewHolder.list_item.setBackgroundColor(Color.WHITE);
            // viewHolder.btn_delete.setTextColor(Color.BLACK);
        }

        //给按钮设置点击事件
        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //list.remove(position); //删除这个条目,在主方法操作
                if (iDeleteData != null) {
                    iDeleteData.deleteData(position);//传递数据（点击的条目的序列号）
                }


            }
        });
        return convertView;
    }


    //创建ViewHolder类
    class ViewHolder {
        TextView maxPress;
        TextView minPress;
        TextView heartRate;
        TextView pulseRate;
        TextView time;
        Button btn_delete;
        LinearLayout list_item;

        ViewHolder(View view) {
            time = (TextView) view.findViewById(R.id.tv_time);
            maxPress = (TextView) view.findViewById(R.id.tv_maxPress);
            minPress = (TextView) view.findViewById(R.id.tv_minPress);
            heartRate = (TextView) view.findViewById(R.id.tv_heartRate);
            pulseRate = (TextView) view.findViewById(R.id.tv_pulseRate);
            btn_delete = (Button) view.findViewById(R.id.btn_delete);
            list_item = (LinearLayout) view.findViewById(R.id.list_item);
        }


    }


    //设置一个回调接口
    public interface IDeleteData {
        void deleteData(int position);
    }

    //定义接口对象
    IDeleteData iDeleteData;
    //设置监听对象


    public void setiDeleteData(IDeleteData iDeleteData) {
        this.iDeleteData = iDeleteData;
    }
}
