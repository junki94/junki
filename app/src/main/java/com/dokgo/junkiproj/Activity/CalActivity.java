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
import com.dokgo.junkiproj.DB.InnerDB;
import com.dokgo.junkiproj.Data.CalData;
import com.dokgo.junkiproj.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CalActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    DayViewDecorator oneDayDecorator;
    MaterialCalendarView materialCalendarView;
    private ArrayList<CalData> datas;
    private InnerDB innerDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        innerDB = new InnerDB(getApplicationContext(),"jkDB",null,1);
        recyclerView = (RecyclerView)findViewById(R.id.cal_recycle);
        datas = getDataFromInnerDB(new Date());
        adapter = new MyAdapter(datas,1);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        oneDayDecorator = new OneDayDecorator();
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017,0,1))
                .setMaximumDate(CalendarDay.from(2030,11,31))
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
                    datas = getDataFromInnerDB(date.getDate());
                    adapter.notifyDataSetChanged();
                    recyclerView.invalidate();
                }
            }
        });
    }
    private ArrayList<CalData> getDataFromInnerDB(Date date){

        ArrayList<ArrayList<String>> innerDBdatas = innerDB.getResult(date);
        int cnt = innerDBdatas.size();
        Log.e("innerDB size",Integer.toString(cnt));
        ArrayList<CalData> finalData = new ArrayList<CalData>();
        CalData CalViewData;
        for(int i=0;i<cnt;i++){
            CalViewData = new CalData();
            CalViewData.setName(innerDBdatas.get(i).get(0));
            CalViewData.setAddr(innerDBdatas.get(i).get(1));
            CalViewData.setMemo(innerDBdatas.get(i).get(2));
            Log.e(innerDBdatas.get(i).get(0)+innerDBdatas.get(i).get(1)+innerDBdatas.get(i).get(2),"날짜: "+innerDBdatas.get(i).get(3));
            finalData.add(CalViewData);
        }

        return finalData;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CalActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
