package com.custum.calendar.CalendarView.Decorations;

import android.content.Context;
import android.graphics.Rect;

import com.custum.calendar.CalendarView.DayView;
import com.custum.calendar.CalendarView.Event;
import com.custum.calendar.CalendarView.EventView;


public class CdvDecorationDefault implements CdvDecoration {

    protected Context mContext;

    public CdvDecorationDefault(Context context) {
        this.mContext = context;
    }

    @Override
    public EventView getEventView(Event event, Rect eventBound, int hourHeight, int separateHeight) {
        EventView eventView = new EventView(mContext);
        eventView.setEvent(event);
//        String description =  jobListItem.customer +  " " + jobListItem.location;
//        event.setName(jobListItem.job_id);
//        eventView.setEventText(description);
        eventView.setPosition(eventBound, -hourHeight, hourHeight - separateHeight);
        event.setScrollValue(eventBound.top + (-hourHeight) - 280);
        return eventView;
    }
    @Override
    public DayView getDayView(int hour) {
        DayView dayView = new DayView(mContext);
        dayView.setText(String.format("%1$2s:00", hour));
        return dayView;
    }

}
