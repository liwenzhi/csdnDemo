package com.example.dialog;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * 签名数据d适配器
 */
public class SignatureAdapter extends BaseOfAdapter<SignatureBean> {

    public HashMap<Integer, Boolean> states = new HashMap<Integer, Boolean>();//用于记录每个RadioButton的状态，并保证只可选一个

    public SignatureAdapter(Context context, List<SignatureBean> list) {
        super(context, list);
    }

    public SignatureAdapter(Context context, SignatureBean[] list) {
        super(context, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_dialog_signature, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText("" + getItem(position).getSignarureName());
        viewHolder.imageView.setImageResource(getItem(position).getPictureID());


        //在这里要做判断保证只有一个RadioButton被选中
        //当RadioButton被选中时，将其状态记录进States中，并更新其他RadioButton的状态使它们不被选中
        viewHolder.rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把所有的按钮的状态设置为没选中
                for (int i = 0; i < getCount(); i++) {
                    states.put(i, false);
                }
                //然后设置点击的那个按钮设置状态为选中
                states.put(position, true);    //这样所有的条目中只有一个被选中！
                notifyDataSetChanged();//刷新适配器
            }
        });

        //上面是点击后设置状态，但是也是需要设置显示样式,通过判断状态设置显示的样式
        if (states.get((Integer) position) == null || states.get((Integer) position) == false) {  //true说明没有被选中
            viewHolder.rb.setChecked(false);
        } else {
            viewHolder.rb.setChecked(true);
        }

        return convertView;
    }


    class ViewHolder {
        TextView textView;
        ImageView imageView;
        RadioButton rb;

        ViewHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.tv_info);
            imageView = (ImageView) convertView.findViewById(R.id.iv_picture);
            rb = (RadioButton) convertView.findViewById(R.id.rb);

        }
    }


}
