package com.example.hellochat;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.*;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {


    private LineChartView chart;
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineChartView chart = (LineChartView) findViewById(R.id.chart);

        chart.setOnValueTouchListener(new ValueTouchListener());

        // Generate some random values.
        generateValues();

        generateData();

        // Disable viewport recalculations, see toggleCubic() method for more info.
        chart.setViewportCalculationEnabled(false);

        resetViewport();


        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 10));
        values.add(new PointValue(3, 4));
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);
        chart.animationDataUpdate(0.5f);
        chart.setLineChartData(data);
    }

    /**
     * menu.add((int groupId, int itemId, int order, charsequence title) .setIcon(drawable ID)
     * add()方法的四个参数，依次是：
     * <p/>
     * 1、组别，如果不分组的话就写Menu.NONE,
     * <p/>
     * 2、Id，这个很重要，Android根据这个Id来确定不同的菜单
     * <p/>
     * 3、顺序，哪个菜单项在前面由这个参数的大小决定
     * <p/>
     * 4、文本，菜单项的显示文本
     *
     * @param menu
     * @param inflater
     */
    // MENU
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //  inflater.inflate(R.menu.line_chart, menu);
//        menu.add(Menu.NONE, 1, 1, "Reset chart");
//        menu.add(Menu.NONE, 2, 2, "Add line");
//        menu.add(Menu.NONE, 3, 3, "Toggle lines/scattered");
//        menu.add(Menu.NONE, 4, 4, "Toggle points");
//        menu.add(Menu.NONE, 5, 5, "Toggle gradient");
//        menu.add(Menu.NONE, 6, 6, "Toggle Cubic curve");
//        menu.add(Menu.NONE, 7, 7, "Toggle area");
//        menu.add(Menu.NONE, 8, 8, "Toggle Point Color");
//        menu.add(Menu.NONE, 9, 9, "Circle shape");
//        menu.add(Menu.NONE, 10, 10, "Square shape");
//        menu.add(Menu.NONE, 11, 11, "Diamond shape");
//        menu.add(Menu.NONE, 12, 12, "Toggle axes");
//        menu.add(Menu.NONE, 13, 13, "Toggle axes names");
//        menu.add(Menu.NONE, 14, 14, "Toggle labels");
//        menu.add(Menu.NONE, 15, 15, "Animate chart");
//        menu.add(Menu.NONE, 16, 16, "Toggle selection mode");
//        menu.add(Menu.NONE, 17, 17, "Toggle touch zoom");
//        menu.add(Menu.NONE, 18, 18, "Zoom X/Y");
//        menu.add(Menu.NONE, 19, 19, "Zoom X");
//        menu.add(Menu.NONE, 20, 20, "Zoom Y");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == 1) {
            reset();
            generateData();
            return true;
        }
        if (id == 2) {
            addLineToData();
            return true;
        }
        if (id == 3) {
            toggleLines();
            return true;
        }
        if (id == 4) {
            togglePoints();
            return true;
        }
        if (id == 5) {
            toggleGradient();
            return true;
        }
        if (id == 6) {
            toggleCubic();
            return true;
        }
        if (id == 7) {
            toggleFilled();
            return true;
        }
        if (id == 8) {
            togglePointColor();
            return true;
        }
        if (id == 9) {
            setCircles();
            return true;
        }
        if (id == 10) {
            setSquares();
            return true;
        }
        if (id == 11) {
            setDiamonds();
            return true;
        }
        if (id == 12) {
            toggleLabels();
            return true;
        }
        if (id == 13) {
            toggleAxes();
            return true;
        }
        if (id == 14) {
            toggleAxesNames();
            return true;
        }
        if (id == 15) {
            prepareDataAnimation();
            chart.startDataAnimation();
            return true;
        }
        if (id == 16) {
            toggleLabelForSelected();

            Toast.makeText(this,
                    "Selection mode set to " + chart.isValueSelectionEnabled() + " select any point.",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == 17) {
            chart.setZoomEnabled(!chart.isZoomEnabled());
            Toast.makeText(this, "IsZoomEnabled " + chart.isZoomEnabled(), Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == 18) {
            chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
            return true;
        }
        if (id == 19) {
            chart.setZoomType(ZoomType.HORIZONTAL);
            return true;
        }
        if (id == 20) {
            chart.setZoomType(ZoomType.VERTICAL);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }

    private void reset() {
        numberOfLines = 1;

        hasAxes = true;
        hasAxesNames = true;
        hasLines = true;
        hasPoints = true;
        shape = ValueShape.CIRCLE;
        isFilled = false;
        hasLabels = false;
        isCubic = false;
        hasLabelForSelected = false;
        pointsHaveDifferentColor = false;

        chart.setValueSelectionEnabled(hasLabelForSelected);
        resetViewport();
    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 100;
        v.left = 0;
        v.right = numberOfPoints - 1;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            //   line.setHasGradientToTransparent(hasGradientToTransparent);
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }

    /**
     * Adds lines to data, after that data should be set again with
     * {@link LineChartView#setLineChartData(LineChartData)}. Last 4th line has non-monotonically x values.
     */
    private void addLineToData() {
        if (data.getLines().size() >= maxNumberOfLines) {
            Toast.makeText(this, "Samples app uses max 4 lines!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ++numberOfLines;
        }

        generateData();
    }

    private void toggleLines() {
        hasLines = !hasLines;

        generateData();
    }

    private void togglePoints() {
        hasPoints = !hasPoints;

        generateData();
    }

    private void toggleGradient() {
        hasGradientToTransparent = !hasGradientToTransparent;

        generateData();
    }

    private void toggleCubic() {
        isCubic = !isCubic;

        generateData();

        if (isCubic) {
            // It is good idea to manually set a little higher max viewport for cubic lines because sometimes line
            // go above or below max/min. To do that use Viewport.inest() method and pass negative value as dy
            // parameter or just set top and bottom values manually.
            // In this example I know that Y values are within (0,100) range so I set viewport height range manually
            // to (-5, 105).
            // To make this works during animations you should use Chart.setViewportCalculationEnabled(false) before
            // modifying viewport.
            // Remember to set viewport after you call setLineChartData().
            final Viewport v = new Viewport(chart.getMaximumViewport());
            v.bottom = -5;
            v.top = 105;
            // You have to set max and current viewports separately.
            chart.setMaximumViewport(v);
            // I changing current viewport with animation in this case.
            chart.setCurrentViewportWithAnimation(v);
        } else {
            // If not cubic restore viewport to (0,100) range.
            final Viewport v = new Viewport(chart.getMaximumViewport());
            v.bottom = 0;
            v.top = 100;

            // You have to set max and current viewports separately.
            // In this case, if I want animation I have to set current viewport first and use animation listener.
            // Max viewport will be set in onAnimationFinished method.
            chart.setViewportAnimationListener(new ChartAnimationListener() {

                @Override
                public void onAnimationStarted() {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationFinished() {
                    // Set max viewpirt and remove listener.
                    chart.setMaximumViewport(v);
                    chart.setViewportAnimationListener(null);

                }
            });
            // Set current viewpirt with animation;
            chart.setCurrentViewportWithAnimation(v);
        }

    }

    private void toggleFilled() {
        isFilled = !isFilled;

        generateData();
    }

    private void togglePointColor() {
        pointsHaveDifferentColor = !pointsHaveDifferentColor;

        generateData();
    }

    private void setCircles() {
        shape = ValueShape.CIRCLE;

        generateData();
    }

    private void setSquares() {
        shape = ValueShape.SQUARE;

        generateData();
    }

    private void setDiamonds() {
        shape = ValueShape.DIAMOND;

        generateData();
    }

    private void toggleLabels() {
        hasLabels = !hasLabels;

        if (hasLabels) {
            hasLabelForSelected = false;
            chart.setValueSelectionEnabled(hasLabelForSelected);
        }

        generateData();
    }

    private void toggleLabelForSelected() {
        hasLabelForSelected = !hasLabelForSelected;

        chart.setValueSelectionEnabled(hasLabelForSelected);

        if (hasLabelForSelected) {
            hasLabels = false;
        }

        generateData();
    }

    private void toggleAxes() {
        hasAxes = !hasAxes;

        generateData();
    }

    private void toggleAxesNames() {
        hasAxesNames = !hasAxesNames;

        generateData();
    }

    /**
     * method(don't confuse with View.animate()). If you operate on data that was set before you don't have to call
     * {@link LineChartView#setLineChartData(LineChartData)} again.
     */
    private void prepareDataAnimation() {
        for (Line line : data.getLines()) {
            for (PointValue value : line.getValues()) {
                // Here I modify target only for Y values but it is OK to modify X targets as well.
                value.setTarget(value.getX(), (float) Math.random() * 100);
            }
        }
    }


    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(MyActivity.this, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
