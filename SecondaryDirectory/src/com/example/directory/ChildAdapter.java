package com.example.directory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 *
 */
public class ChildAdapter extends BaseOfAdapter<String> {
    public ChildAdapter(Context context, List<String> list) {
        super(context, list);
    }

    public ChildAdapter(Context context, String[] list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item2, null);
        }
        TextView tv_item = (TextView) convertView.findViewById(R.id.tv_item);
        tv_item.setText("" + getItem(position));
        return convertView;
    }
}
