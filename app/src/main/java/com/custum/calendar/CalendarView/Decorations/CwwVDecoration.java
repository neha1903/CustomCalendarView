package com.custum.calendar.CalendarView.Decorations;

import android.graphics.Rect;

import com.custum.calendar.CalendarView.Event;
import com.custum.calendar.CalendarView.EventWorkWeekView;
import com.custum.calendar.CalendarView.WorkWeekView;


public interface CwwVDecoration {

    EventWorkWeekView getEventView(Event event, Rect eventBound, int hourHeight, int seperateHeight, int seprateWidth);
    WorkWeekView getDayView(int hour);
}
