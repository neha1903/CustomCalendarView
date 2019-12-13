package com.custum.calendar.CalendarView.Decorations;

import android.graphics.Rect;

import com.custum.calendar.CalendarView.DayView;
import com.custum.calendar.CalendarView.Event;
import com.custum.calendar.CalendarView.EventView;


public interface CdvDecoration {

    EventView getEventView(Event event, Rect eventBound, int hourHeight, int seperateHeight);
    DayView getDayView(int hour);
}
