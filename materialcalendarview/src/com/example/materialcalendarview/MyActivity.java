package com.example.materialcalendarview;

import android.app.Activity;
import android.os.Bundle;
import com.example.materialcalendarview.materialcalendarview.MaterialCalendarView;

/**
 * 简单日历示例程序
 * Android Dex: [materialcalendarview]
 * Caused by: java.lang.UnsupportedClassVersionError: com/android/dx/command/dexer/Main : Unsupported major.minor version 52.0
 */
public class MyActivity extends Activity {

    MaterialCalendarView materialCalendarView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
//
//
//        materialCalendarView.state().edit()
//                .setFirstDayOfWeek(Calendar.WEDNESDAY)
//                .setMinimumDate(CalendarDay.from(2016, 4, 3))
//                .setMaximumDate(CalendarDay.from(2016, 5, 12))
//                .setCalendarDisplayMode(CalendarMode.WEEKS)
//                .commit();
    }


}
