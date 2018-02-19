package com.dokgo.junkiproj.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dokgo.junkiproj.Adapter.MyAdapter;
import com.dokgo.junkiproj.Calendar.OneDayDecorator;
import com.dokgo.junkiproj.Calendar.SaturdayDecorator;
import com.dokgo.junkiproj.Calendar.SundayDecorator;
import com.dokgo.junkiproj.Data.CalData;
import com.dokgo.junkiproj.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;


public class CalActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    DayViewDecorator oneDayDecorator;
    MaterialCalendarView materialCalendarView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        recyclerView = (RecyclerView)findViewById(R.id.cal_recycle);
        adapter = new MyAdapter(getDataFromDB(),1);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        oneDayDecorator = new OneDayDecorator();
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017,0,1))
                .setMaximumDate(CalendarDay.from(2030,11,30))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if(selected){
                    Log.e("년 : ",Integer.toString(date.getYear()));
                    Log.e("월 : ",Integer.toString(date.getMonth())+1); // 월은 1 더해야 댐
                    Log.e("일 : ",Integer.toString(date.getDay()));
                }
            }
        });
    }
    private ArrayList<CalData> getDataFromDB(){
        ArrayList<CalData> finalData = new ArrayList<CalData>();
        CalData CalViewData = new CalData();
        CalViewData.setName("송준기");
        CalViewData.setAddr("군대");
        CalViewData.setMemo("특이사항 없음");
        finalData.add(CalViewData);
        CalViewData = new CalData();
        CalViewData.setName("김준기");
        CalViewData.setAddr("송도");
        CalViewData.setMemo("게임 중독 증상 있음");
        finalData.add(CalViewData);
        CalViewData = new CalData();
        CalViewData.setName("조수근");
        CalViewData.setAddr("인천");
        CalViewData.setMemo("알코올 중독 증상 심각");
        finalData.add(CalViewData);
        CalViewData = new CalData();
        CalViewData.setName("이수근");
        CalViewData.setAddr("이천");
        CalViewData.setMemo("감기 기운 있음..");
        finalData.add(CalViewData);
        CalViewData = new CalData();
        CalViewData.setName("박준기");
        CalViewData.setAddr("대천");
        CalViewData.setMemo("항상 배고파함..");
        finalData.add(CalViewData);
        CalViewData = new CalData();
        CalViewData.setName("호호호");
        CalViewData.setAddr("없음");
        CalViewData.setMemo("관리 필요");
        finalData.add(CalViewData);

        return finalData;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CalActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
