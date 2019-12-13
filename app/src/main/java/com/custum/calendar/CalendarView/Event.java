package com.custum.calendar.CalendarView;

import java.util.Calendar;


public class Event{

    private Calendar mStartTime;
    private Calendar mEndTime;
    private String ID;
    private String mLocation;
    private int scrollValue = 0;

    public Event(Calendar mStartTime, Calendar mEndTime, String ID, String mLocation, int scrollValue){
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.ID = ID;
        this.mLocation = mLocation;
        this.scrollValue = scrollValue;
    }

    public int getScrollValue() {
        return scrollValue;
    }

    public void setScrollValue(int scrollValue) {
        this.scrollValue = scrollValue;
    }

    public Calendar getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Calendar startTime) {
        this.mStartTime = startTime;
    }

    public Calendar getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Calendar endTime) {
        this.mEndTime = endTime;
    }

    public String getName() {
        return ID;
    }

    public void setName(String name) {
        this.ID = name;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

}
