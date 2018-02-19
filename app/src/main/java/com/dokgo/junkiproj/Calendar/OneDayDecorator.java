package com.dokgo.junkiproj.Calendar;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Date;

/**
 * Created by user on 2018-02-07.
 */

public class OneDayDecorator implements DayViewDecorator {

    private CalendarDay date;

    public OneDayDecorator() {
        date = CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new StyleSpan(Typeface.BOLD)); // 글씨체
        view.addSpan(new RelativeSizeSpan(1.4f)); // 글자 크기
        view.addSpan(new ForegroundColorSpan(Color.GREEN)); // 날짜 색깔 바꾸는 곳
    }

    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}

