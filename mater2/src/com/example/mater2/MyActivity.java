package com.example.mater2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;
import com.example.materialcalendarview.materialcalendarview.CalendarDay;
import com.example.materialcalendarview.materialcalendarview.CalendarMode;
import com.example.materialcalendarview.materialcalendarview.MaterialCalendarView;
import com.example.materialcalendarview.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class MyActivity extends Activity {
    MaterialCalendarView materialCalendarView;
    CalendarDay currentDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);


        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))
                .setMaximumDate(CalendarDay.from(2018, 5, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                currentDate = date;
            }
        });
    }

    public void getTime(View view) {
        if (currentDate != null) {
            int year = currentDate.getYear();
            int month = currentDate.getMonth() + 1;
            int day = currentDate.getDay();
            Toast.makeText(this, currentDate.toString() + "你选中的是：" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "请选择时间", Toast.LENGTH_LONG).show();
        }


    }

}
