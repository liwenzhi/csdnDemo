package com.example.hellochat2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Hellochat的使用示例
     */

    LineChartView mLineChartView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mLineChartView = (LineChartView) findViewById(R.id.lineCharView);
        List<PointValue> values = new ArrayList<PointValue>();

        for (int i = 0; i < 100; i++) {
            values.add(new PointValue(i * 10 + 1, (float) Math.random() * 10f));
        }

        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        mLineChartView.setLineChartData(data);
        mLineChartView.setInteractive(true);
        mLineChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
    }
}
