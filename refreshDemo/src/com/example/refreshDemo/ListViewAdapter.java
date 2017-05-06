package com.example.refreshDemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * ListView适配器
 *
 * @author w.w
 */
public class ListViewAdapter extends BaseOfAdapter<String> {


    public ListViewAdapter(Context context, List<String> list) {
        super(context, list);
    }

    public ListViewAdapter(Context context, String[] list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview, null);
        }
        TextView item_name = (TextView) convertView.findViewById(R.id.item_name);
        item_name.setText("" + getItem(position));
        return convertView;
    }
}
