package com.example.directory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 *
 */
public class MenuAdapter extends BaseOfAdapter<String> {
    private int selectedPosition = -1;

    public MenuAdapter(Context context, List<String> list) {
        super(context, list);
    }

    public MenuAdapter(Context context, String[] list) {
        super(context, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item, null);
        }
        TextView tv_item = (TextView) convertView.findViewById(R.id.tv_item);
        tv_item.setText("" + getItem(position));
        if (selectedPosition == position) {
            tv_item.setSelected(true);
        }else{
            tv_item.setSelected(false);
        }
        return convertView;
    }

    interface Listener {
        void onClick(int position);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

}
