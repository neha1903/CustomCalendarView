package com.custum.calendar.CalendarView.Decorations;

import android.graphics.Rect;

import com.custum.calendar.CalendarView.Event;
import com.custum.calendar.CalendarView.EventWeekView;
import com.custum.calendar.CalendarView.WeekView;


public interface CwVDecoration {

    EventWeekView getEventView(Event event, Rect eventBound, int hourHeight, int seperateHeight, int seprateWidth);
    WeekView getDayView(int hour);
}
