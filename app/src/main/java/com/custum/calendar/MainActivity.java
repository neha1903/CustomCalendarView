package com.custum.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.custum.calendar.CalendarView.CalendarDayView;
import com.custum.calendar.CalendarView.CalendarView;
import com.custum.calendar.CalendarView.CalendarWeekView;
import com.custum.calendar.CalendarView.CalendarWorkWeekView;
import com.custum.calendar.CalendarView.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private ListView jobList;
    private CalendarView cv;
    private ArrayList<Event> events;
    private LinearLayout dayView;
    private LinearLayout weekView;
    private LinearLayout workWeekView;
    private LinearLayout monthView;
    private ImageView dayViewImage;
    private ImageView weekViewImage;
    private ImageView workWeekImage;
    private ImageView monthViewImage;

    private TextView dayViewText;
    private TextView weekViewText;
    private TextView workWeekText;
    private TextView monthViewText;
    private boolean DayViewClick = false;
    private boolean WeekViewClick =  false;
    private boolean WorkWeekViewClick =  false;
    private LinearLayout dayViewContainer;
    private LinearLayout weekViewContainer;
    private LinearLayout workWeekViewContainer;

    private CalendarDayView calenderDayView;

    private ScrollView scrollView;

    private Calendar currentDate = Calendar.getInstance();
    private ScrollView weekScrollView;
    private ScrollView workWeekScrollView;

    private CalendarWeekView calendarWeekView;
    private CalendarWorkWeekView calendarWorkWeekView;

    private HashSet<Date> eventDays = new HashSet<Date>();
    private SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv = ((CalendarView) findViewById(R.id.calendarView));
        dayView = (LinearLayout) findViewById(R.id.day_view);
        weekView =(LinearLayout) findViewById(R.id.week_view);
        workWeekView = (LinearLayout) findViewById(R.id.woork_week_view);
        monthView = (LinearLayout) findViewById(R.id.month_view);
        dayViewImage = (ImageView) findViewById(R.id.day_view_image);
        weekViewImage = (ImageView) findViewById(R.id.week_view_image);
        workWeekImage = (ImageView) findViewById(R.id.work_week_image);
        monthViewImage = (ImageView) findViewById(R.id.month_view_image);
        dayViewText = (TextView) findViewById(R.id.day_view_text);
        weekViewText = (TextView) findViewById(R.id.week_view_text);
        workWeekText = (TextView) findViewById(R.id.work_week_text);
        monthViewText = (TextView) findViewById(R.id.month_view_text);
        calenderDayView = (CalendarDayView) findViewById(R.id.calendar_day_view);
        dayViewContainer = (LinearLayout) findViewById(R.id.dayview_container);
        scrollView = (ScrollView) findViewById(R.id.ScrollBar);
        weekViewContainer = (LinearLayout) findViewById(R.id.week_view_layout);
        workWeekViewContainer =(LinearLayout) findViewById(R.id.week_work_layout);
        weekScrollView = (ScrollView) findViewById(R.id.weekScroll);
        calendarWeekView =  (CalendarWeekView) findViewById(R.id.calendar_week_view);
        calendarWorkWeekView =  (CalendarWorkWeekView) findViewById(R.id.calendar_work_week_view);
        workWeekScrollView = (ScrollView) findViewById(R.id.weekworkScroll);

        cv.updateCalendar();
        fillEvents();
        assignOnClickListener();

    }

    private void fillEvents() {
        eventDays = new HashSet<Date>();
        Calendar cal = Calendar.getInstance();
        eventDays.add(cal.getTime());
        for(int i =0; i<5; i++){
            cal.add(Calendar.DAY_OF_MONTH, 1);
            eventDays.add(cal.getTime());
        }


        events = new ArrayList<>();
        {
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 11);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 15);
            timeEnd.set(Calendar.MINUTE, 45);
            Event event = new Event( timeStart, timeEnd, "Event", "Hockaido",0);
            events.add(event);
        }

        {
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 18);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 20);
            timeEnd.set(Calendar.MINUTE, 30);
            Event event = new Event( timeStart, timeEnd, "Another event", "Hockaido", 0);
            events.add(event);
        }

        {
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 12);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 13);
            timeEnd.set(Calendar.MINUTE, 30);
            Event event = new Event(timeStart, timeEnd, "Another event", "Hockaido", 0);
            events.add(event);
        }

        {
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 20);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 22);
            timeEnd.set(Calendar.MINUTE, 0);
            Event event = new Event( timeStart, timeEnd, "Another event", "Hockaido", 0);
            events.add(event);
        }

        calenderDayView.setEvents(events);
        calendarWeekView.setEvents(events);
        calendarWorkWeekView.setEvents(events);
    }

    public void dateOnClick(){
        Date d = cv.getCurrentDate();
        currentDate.setTime(d);
        cv.setCurrentDate(d);
    }

    private void setNotSelected(){
        dayView.setBackgroundResource(R.drawable.shadow_115330);
        dayViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_day));
        dayViewText.setTextColor(Color.parseColor("#534a4a4a"));

        weekView.setBackgroundResource(R.drawable.shadow_115330);
        weekViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_week));
        weekViewText.setTextColor(Color.parseColor("#534a4a4a"));

        workWeekView.setBackgroundResource(R.drawable.shadow_115330);
        workWeekImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_work_week));
        workWeekText.setTextColor(Color.parseColor("#534a4a4a"));

        monthView.setBackgroundResource(R.drawable.shadow_115330);
        monthViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_month));
        monthViewText.setTextColor(Color.parseColor("#534a4a4a"));

    }

    public  void visibilityGone(){
        cv.setDayViewClick(false);
        dayViewContainer.setVisibility(View.GONE);
        weekViewContainer.setVisibility(View.GONE);
        workWeekViewContainer.setVisibility(View.GONE);
    }

    public void setClick(){
        cv.setWeekViewClick(false);
        WeekViewClick = false;
        cv.setDayViewClick(false);
        DayViewClick = false;
        WorkWeekViewClick = false;
        cv.setWorkWeekViewClick(false);
    }

    public void assignOnClickListener(){

        findViewById(R.id.calendar_prev_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.setButtonPrev();
                Date d = cv.getCurrentDate();
                currentDate.setTime(d);
                cv.setCurrentDate(d);
                if(DayViewClick){
                    fillEvents();
                    /*GetContainerListDay getContainerListDay = new GetContainerListDay();
                    getContainerListDay.execute();*/
                }else if(WeekViewClick){
                    /*GetContainerListWeek getContainerListWeek = new GetContainerListWeek();
                    getContainerListWeek.execute();*/
                }else if(WorkWeekViewClick){
                    /*GetContainerListWorkWeek getContainerListWorkWeek = new GetContainerListWorkWeek();
                    getContainerListWorkWeek.execute();*/
                }else{
                    cv.updateCalendar(eventDays);
                    /*GetContainerListMonth getContainerListMonth = new GetContainerListMonth();
                    getContainerListMonth.execute();*/
                }
                /*GetContainerList getContainerList = new GetContainerList();
                getContainerList.execute();*/
            }
        });

        findViewById(R.id.calendar_next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.setButtonNext();
                Date d = cv.getCurrentDate();
                currentDate.setTime(d);
                cv.setCurrentDate(d);
                /*GetContainerList getContainerList = new GetContainerList();
                getContainerList.execute();*/
                if(DayViewClick){
                    /*GetContainerListDay getContainerListDay = new GetContainerListDay();
                    getContainerListDay.execute();*/
                }else if(WeekViewClick){
                   /* GetContainerListWeek getContainerListWeek = new GetContainerListWeek();
                    getContainerListWeek.execute();*/
                }else if(WorkWeekViewClick){
                    /*GetContainerListWorkWeek getContainerListWorkWeek = new GetContainerListWorkWeek();
                    getContainerListWorkWeek.execute();*/
                }else{
                    cv.updateCalendar(eventDays);
                    /*GetContainerListMonth getContainerListMonth = new GetContainerListMonth();
                    getContainerListMonth.execute();*/
                }

            }
        });


        findViewById(R.id.calendar_grid);
        GridView gridView = findViewById(R.id.calendar_grid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cv.gridOnClick(parent, view, position);
                /*GetContainerListMonth getContainerListMonth = new GetContainerListMonth();
                getContainerListMonth.execute();*/
                cv.updateCalendar(eventDays);
                dateOnClick();
            }
        });
        GridView gridView1 = findViewById(R.id.calendar_week_grid);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cv.weekCalanderGridOnClick(parent, view, position);
                dateOnClick();
            }
        });



        dayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNotSelected();
                dayView.setBackgroundResource(R.drawable.shadow_17855);
                dayViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_day_selected));
                dayViewText.setTextColor(Color.parseColor("#bb1f2c"));
                visibilityGone();
                setClick();
                dayViewContainer.setVisibility(View.VISIBLE);
                DayViewClick = true;
                cv.setDayViewClick(true);
                Date d = cv.getCurrentDate();
                currentDate.setTime(d);
                /*GetContainerListDay getContainerListDay = new GetContainerListDay();
                getContainerListDay.execute();*/
            }
        });

        weekView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNotSelected();
                weekView.setBackgroundResource(R.drawable.shadow_17855);
                weekViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_week_selected));
                weekViewText.setTextColor(Color.parseColor("#bb1f2c"));
                visibilityGone();
                setClick();
                weekViewContainer.setVisibility(View.VISIBLE);
                WeekViewClick = true;
                cv.setWeekViewClick(true);
                Date d = cv.getCurrentDate();
                currentDate.setTime(d);
                /*GetContainerListWeek getContainerListWeek = new GetContainerListWeek();
                getContainerListWeek.execute();
                GetContainerList getContainerList = new GetContainerList();
                getContainerList.execute();*/

            }
        });


        workWeekView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNotSelected();
                workWeekView.setBackgroundResource(R.drawable.shadow_17855);
                workWeekImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_work_week_selected));
                workWeekText.setTextColor(Color.parseColor("#bb1f2c"));
                visibilityGone();
                setClick();
                workWeekViewContainer.setVisibility(View.VISIBLE);
                WorkWeekViewClick = true;
                cv.setWorkWeekViewClick(true);
                Date d = cv.getCurrentDate();
                Calendar cal = Calendar.getInstance();
                Date startDate = cv.getCurrentDate();
                cal.setTime(startDate);
                String s = sdfDay.format(startDate);
                if(s.equals("Sunday")) {
                    cal.add(Calendar.DAY_OF_MONTH, -2);
                }else if(s.equals("Saturday")) {
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                }
                cv.setCurrentDate(cal.getTime());
                /*GetContainerListWorkWeek getContainerListWorkWeek = new GetContainerListWorkWeek();
                getContainerListWorkWeek.execute();
                GetContainerList getContainerList = new GetContainerList();
                getContainerList.execute();*/
            }
        });

        monthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNotSelected();
                monthView.setBackgroundResource(R.drawable.shadow_17855);
                monthViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_month_selected));
                monthViewText.setTextColor(Color.parseColor("#bb1f2c"));
                visibilityGone();
                setClick();
                cv.updateCalendar();
                cv.updateCalendar(eventDays);
                /*GetContainerList getContainerList = new GetContainerList();
                getContainerList.execute();
                GetContainerListMonth getContainerListMonth = new GetContainerListMonth();
                getContainerListMonth.execute();*/
            }
        });
    }
}
