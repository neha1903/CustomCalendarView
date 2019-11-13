package com.custum.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by NeHa on 15/10/2019.
 */
public class CalendarView extends LinearLayout{
	// for logging
	private static final String LOGTAG = "Calendar View";

	// how many days to show, defaults to six weeks, 42 days
	private static final int DAYS_COUNT = 42;

	// default date format
	private static final String DATE_FORMAT = "MMM yyyy";

	// date format
	private String dateFormat;

	// current displayed month
	private Calendar currentDate = Calendar.getInstance();

	//event handling
	private EventHandler eventHandler = null;

	// internal components
	private LinearLayout header;
	private ImageView btnPrev;
	private ImageView btnNext;
	//private TextView txtDate;
	private GridView grid;

	private LinearLayout linearDayView;

	private TextView displayMonth;

	private TextView displayYear;

	int monthNo , yearNo;

	Date currentSelectedDate = null;
	Date prevSelectedDate = null;
	View currentDateView;
	View prevDateView;

	TextView mondayView;
	TextView tuesdayView;
	TextView wednesdayView;
	TextView thursdayView;
	TextView fridayView;
	TextView saturdayView;
	TextView sundayView;
	LinearLayout dayView;
	LinearLayout weekView;
	LinearLayout workWeekView;
	LinearLayout monthView;

	ImageView dayViewImage;
	ImageView weekViewImage;
	ImageView workWeekImage;
	ImageView monthViewImage;

	TextView dayViewText;
	TextView weekViewText;
	TextView workWeekText;
	TextView monthViewText;

	boolean DayViewClick = true;
	boolean WeekViewClick =  true;

	TextView dayViewDate;
	TextView headerDay;

	private LinearLayout mLayoutDayView;


	public CalendarView(Context context) {
		super(context);
	}

	public CalendarView(Context context, AttributeSet attrs){
		super(context, attrs);
		initControl(context, attrs);
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyleAttr){
		super(context, attrs, defStyleAttr);
		initControl(context, attrs);
	}

	String[] MonthName = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October","November", "December"};

	/**
	 * Load control xml layout
	 */
	private void initControl(Context context, AttributeSet attrs){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.control_calendar, this);

		loadDateFormat(attrs);
		assignUiElements();
		assignClickHandlers();

		updateCalendar();
	}

	private void loadDateFormat(AttributeSet attrs){
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

		try
		{
			// try to load provided date format, and fallback to default otherwise
			dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
			if (dateFormat == null){
				dateFormat = DATE_FORMAT;
			}
		}
		finally
		{
			ta.recycle();
		}
	}

	private void assignUiElements(){
		// layout is inflated, assign local variables to components
		header = (LinearLayout)findViewById(R.id.calendar_header_month);
		headerDay = (TextView) findViewById(R.id.day_view_cd);
		btnPrev = (ImageView)findViewById(R.id.calendar_prev_button);
		btnNext = (ImageView)findViewById(R.id.calendar_next_button);
		//txtDate = (TextView)findViewById(R.id.calendar_date_display);
		grid = (GridView)findViewById(R.id.calendar_grid);

		linearDayView = (LinearLayout) findViewById(R.id.linear_day_view);

		dayViewDate = (TextView) findViewById(R.id.date_view_cd);



		displayMonth =(TextView) findViewById(R.id.calendar_month_display);
		displayYear = (TextView) findViewById(R.id.calendar_year_display);
		mondayView = (TextView) findViewById(R.id.mondayText);
		tuesdayView = (TextView) findViewById(R.id.tuesdayText);
		wednesdayView = (TextView) findViewById(R.id.wednesdayText);
		thursdayView = (TextView) findViewById(R.id.thursdayText);
		fridayView = (TextView) findViewById(R.id.fridayText);
		saturdayView = (TextView) findViewById(R.id.saturdayText);
		sundayView = (TextView) findViewById(R.id.sundayText);


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



		mLayoutDayView = (LinearLayout) findViewById(R.id.dayview_container);

	}

	private void assignClickHandlers(){
		// add one month and refresh UI
		btnNext.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				btnNext.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_white_24dp));
				btnPrev.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_grey_24dp));

				if(DayViewClick){
					currentDate.add(Calendar.DAY_OF_MONTH, 1);
				}else{
					currentDate.add(Calendar.MONTH, 1);

				}

				updateCalendar();
			}
		});

		// subtract one month and refresh UI
		btnPrev.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				btnNext.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_grey_24dp));
				btnPrev.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_24dp));
				if(DayViewClick){
					currentDate.add(Calendar.DAY_OF_MONTH, -1);
				}else{
					currentDate.add(Calendar.MONTH, -1);
				}
				updateCalendar();
			}
		});

		dayView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setNotSelected();
				dayView.setBackgroundResource(R.drawable.shadow_17855);
				dayViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_day_selected));
				dayViewText.setTextColor(Color.parseColor("#bb1f2c"));
				updateCalendar();
				visibilityGone();
				setViewClick();
				linearDayView.setVisibility(VISIBLE);
				headerDay.setVisibility(VISIBLE);
				DayViewClick = true;
			}
		});

		weekView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setNotSelected();
				weekView.setBackgroundResource(R.drawable.shadow_17855);
				weekViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_week_selected));
				weekViewText.setTextColor(Color.parseColor("#bb1f2c"));
				visibilityGone();
				setViewClick();
				updateCalendar();
				header.setVisibility(VISIBLE);

			}
		});

		workWeekView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setNotSelected();
				workWeekView.setBackgroundResource(R.drawable.shadow_17855);
				workWeekImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_work_week_selected));
				workWeekText.setTextColor(Color.parseColor("#bb1f2c"));
				updateCalendar();
				visibilityGone();
				setViewClick();
			}
		});

		monthView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setNotSelected();
				monthView.setBackgroundResource(R.drawable.shadow_17855);
				monthViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_month_selected));
				monthViewText.setTextColor(Color.parseColor("#bb1f2c"));
				updateCalendar();
				visibilityGone();
				setViewClick();
				grid.setVisibility(VISIBLE);
				header.setVisibility(VISIBLE);
			}
		});



		/*Date today = new Date();
		Calendar todaySelected = Calendar.getInstance();
		todaySelected.setTime(today);
		int todayDay = todaySelected.get(Calendar.DAY_OF_MONTH);
		int todayMonth = todaySelected.get(Calendar.MONTH);
		int todayYear = todaySelected.get(Calendar.YEAR);*/

		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Date d = (Date) parent.getItemAtPosition(position);
				setDaysC(d, view);
			}
		});

		// long-pressing a day
		grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id)
			{
				// handle long-press
				if (eventHandler == null){
					return false;
				}

				Toast.makeText(getContext(), view.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();

				eventHandler.onDayLongPress((Date)view.getItemAtPosition(position));
				return true;
			}
		});
	}

	private void setViewClick(){
		DayViewClick = false;
	}

	private void visibilityGone(){
		grid.setVisibility(GONE);
		linearDayView.setVisibility(GONE);
		header.setVisibility(GONE);
		headerDay.setVisibility(GONE);
	}

	private void setNotSelected(){
		dayView.setBackgroundResource(R.drawable.shadow_115330);
		dayViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_day));
		dayViewText.setTextColor(Color.parseColor("#4a4a4a"));

		weekView.setBackgroundResource(R.drawable.shadow_115330);
		weekViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_week));
		weekViewText.setTextColor(Color.parseColor("#4a4a4a"));

		workWeekView.setBackgroundResource(R.drawable.shadow_115330);
		workWeekImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_work_week));
		workWeekText.setTextColor(Color.parseColor("#4a4a4a"));

		monthView.setBackgroundResource(R.drawable.shadow_115330);
		monthViewImage.setImageDrawable(getResources().getDrawable(R.drawable.calendar_month));
		monthViewText.setTextColor(Color.parseColor("#4a4a4a"));

	}



	public void setDaysC(Date d, View view){

		Calendar dSelected = Calendar.getInstance();
		dSelected.setTime(d);
		Date today = new Date();
		Calendar todaySelected = Calendar.getInstance();
		todaySelected.setTime(today);
		int todayDay = todaySelected.get(Calendar.DAY_OF_MONTH);
		int todayMonth = todaySelected.get(Calendar.MONTH);
		int todayYear = todaySelected.get(Calendar.YEAR);



		if (monthNo == dSelected.get(Calendar.MONTH) && yearNo == dSelected.get(Calendar.YEAR)) {
			if (monthNo == todayMonth && dSelected.get(Calendar.DAY_OF_MONTH) == todayDay && yearNo == todayYear) {

			}else {
				if (currentSelectedDate == null){
					currentSelectedDate = d;
					currentDateView = view;
					view.setBackgroundResource(R.drawable.date_selection);
				}else{
					prevDateView = currentDateView;
					prevDateView.setBackgroundResource(0);
					prevSelectedDate = currentSelectedDate;
					currentSelectedDate = d;
					currentDateView = view;
					view.setBackgroundResource(R.drawable.date_selection);
				}
			}

		}
	}

	/**
	 * Display dates correctly in grid
	 */
	public void updateCalendar(){
		updateCalendar(null);
	}

	/**
	 * Display dates correctly in grid
	 */
	public void updateCalendar(HashSet<Date> events){
		ArrayList<Date> cells = new ArrayList<>();
		Calendar calendar = (Calendar)currentDate.clone();
		int day = currentDate.get(Calendar.DAY_OF_MONTH);

		// determine the cell for current month's beginning
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int monthBeginningCell = (calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek()) < 0 ? 7 + (calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek()): (calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek());

		Log.e("DAY Of WEEK ", ""+ monthBeginningCell);

		// move calendar backwards to the beginning of the week
		calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

		// fill cells
		while (cells.size() < DAYS_COUNT)
		{
			cells.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		// update grid
		grid.setAdapter(new CalendarAdapter(getContext(), cells, events));


		int month = currentDate.get(Calendar.MONTH);
		int year = currentDate.get(Calendar.YEAR);



		monthNo = month;
		yearNo = year;
		//int season = monthSeason[month];
		//int color = season;
		String monthName= MonthName[month];
		displayMonth.setText(monthName);
		displayYear.setText(String.valueOf(year));
		Date today = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
		String goal = outFormat.format(today);
		Log.e("DAY : ",goal);
		setDayColorDull(goal);
		Calendar todaySelected = Calendar.getInstance();
		int todayMonth = todaySelected.get(Calendar.MONTH);
		int todayYear = todaySelected.get(Calendar.YEAR);
		int todayDay = todaySelected.get(Calendar.DAY_OF_MONTH);
		//header.setBackgroundColor(getResources().getColor(color));
		if (monthNo == todayMonth && yearNo == todayYear)
		{
			setDayColor(goal);
		}

		SimpleDateFormat outFormat1 = new SimpleDateFormat("EEEE");
		String st = outFormat1.format(currentDate.getTime());
		headerDay.setText(st);
		headerDay.setTextColor(Color.parseColor("#bb1f2c"));
		headerDay.setBackgroundColor(Color.parseColor("#ffeeef"));
		dayViewDate.setText(String.valueOf(currentDate.get(Calendar.DAY_OF_MONTH)));

		if (monthNo == todayMonth && day == todayDay && yearNo == todayYear)
		{
			dayViewDate.setBackgroundResource(R.drawable.today_date);
			headerDay.setTextColor(Color.parseColor("#ffffff"));
			headerDay.setBackgroundColor(Color.parseColor("#bb1f2c"));
		}else{
			dayViewDate.setBackgroundResource(R.drawable.day_bg);
		}

		Log.e("DATE : ", "" + day);
		Log.e("Today DATE : ", "" + todayDay);
		drawDayViews();
	}


	private void drawDayViews(){
		mLayoutDayView.removeAllViews();
		for(int i=0; i<24; i++){
			DayView calendarDayView = new DayView(getContext());
			String a = String.valueOf(i);
			if(i<=9){
				a = "0" + i;
			}
			calendarDayView.setText(a);
			mLayoutDayView.addView(calendarDayView);
		}
	}

	private class CalendarAdapter extends ArrayAdapter<Date>{
		// days with events
		private HashSet<Date> eventDays;

		// for view inflation
		private LayoutInflater inflater;

		public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays)
		{
			super(context, R.layout.control_calendar_day, days);
			this.eventDays = eventDays;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View view, ViewGroup parent)
		{
			Date date = getItem(position);
			Calendar calSelected = Calendar.getInstance();
			calSelected.setTime(date);

			int day = calSelected.get(Calendar.DAY_OF_MONTH);
			int month = calSelected.get(Calendar.MONTH);
			int year = calSelected.get(Calendar.YEAR);

			// today
			Date today = new Date();
			Calendar todaySelected = Calendar.getInstance();
			todaySelected.setTime(today);
			int todayDay = todaySelected.get(Calendar.DAY_OF_MONTH);
			int todayMonth = todaySelected.get(Calendar.MONTH);
			int todayYear = todaySelected.get(Calendar.YEAR);


			// inflate item if it does not exist yet
			if (view == null){
				view = inflater.inflate(R.layout.control_calendar_day, parent, false);
			}

			view.setBackgroundResource(0);
			// clear styling
			((TextView)view).setTypeface(null, Typeface.NORMAL);
			((TextView)view).setTextColor(Color.BLACK);


//			Calendar eventSelected = Calendar.getInstance();
//			calSelected.setTime(today);
//			if (eventSelected.get(Calendar.DAY_OF_MONTH) == day &&
//					eventSelected.get(Calendar.MONTH) == month &&
//					eventSelected.get(Calendar.YEAR) == year)
//			{
//
//				// mark this day for event
//				view.setBackgroundResource(R.drawable.today_date);
//			}




			// if this day has an event, specify event image

			/*if (eventDays != null)
			{
				for (Date eventDate : eventDays)
				{

					Calendar eventSelected = Calendar.getInstance();
					calSelected.setTime(eventDate);
					if (eventSelected.get(Calendar.DAY_OF_MONTH) == day &&
							eventSelected.get(Calendar.MONTH) == month &&
							eventSelected.get(Calendar.YEAR) == year)
					{

						// mark this day for event
						view.setBackgroundResource(R.drawable.today_date);
						break;
					}
				}
			}*/




			if (monthNo != month || yearNo != year)
			{
				// if this day is outside current month, grey it out
				((TextView)view).setTextColor(getResources().getColor(R.color.grey_text));

				if (month == todayMonth && day == todayDay && year == todayYear){
					view.setBackgroundResource(R.drawable.dull_today_date);
				}
			}
			else if (month == todayMonth && day == todayDay && year == todayYear)
			{
				// if it is today, set it to blue/bold
//				((TextView)view).setTypeface(null, Typeface.BOLD);
				view.setBackgroundResource(R.drawable.today_date);

//				((TextView)view).setTextColor(getResources().getColor(R.color.today));
			}

			// set text
			((TextView)view).setText(String.valueOf(day));

			return view;
		}
	}

	public void setDayColorDull(String day){
		if(day.equals("Monday")){
			mondayView.setTextColor(Color.parseColor("#bb1f2c"));
			mondayView.setBackgroundColor(0);
		}else if(day.equals("Tuesday")){
			tuesdayView.setTextColor(Color.parseColor("#bb1f2c"));
			tuesdayView.setBackgroundColor(0);
		}else if(day.equals("Wednesday")){
			wednesdayView.setTextColor(Color.parseColor("#bb1f2c"));
			wednesdayView.setBackgroundColor(0);
		}else if(day.equals("Thursday")){
			thursdayView.setTextColor(Color.parseColor("#bb1f2c"));
			thursdayView.setBackgroundColor(0);
		}else if(day.equals("Friday")){
			fridayView.setTextColor(Color.parseColor("#bb1f2c"));
			fridayView.setBackgroundColor(0);
		}else if(day.equals("Saturday")){
			saturdayView.setTextColor(Color.parseColor("#bb1f2c"));
			saturdayView.setBackgroundColor(0);
		}else if(day.equals("Sunday")){
			sundayView.setTextColor(Color.parseColor("#bb1f2c"));
			sundayView.setBackgroundColor(0);
		}
	}

	public void setDayColor(String day){
		if(day.equals("Monday")){
			mondayView.setTextColor(Color.parseColor("#ffffff"));
			mondayView.setBackgroundColor(Color.parseColor("#bb1f2c"));
		}else if(day.equals("Tuesday")){
			tuesdayView.setTextColor(Color.parseColor("#ffffff"));
			tuesdayView.setBackgroundColor(Color.parseColor("#bb1f2c"));
		}else if(day.equals("Wednesday")){
			wednesdayView.setTextColor(Color.parseColor("#ffffff"));
			wednesdayView.setBackgroundColor(Color.parseColor("#bb1f2c"));
		}else if(day.equals("Thursday")){
			thursdayView.setTextColor(Color.parseColor("#ffffff"));
			thursdayView.setBackgroundColor(Color.parseColor("#bb1f2c"));
		}else if(day.equals("Friday")){
			fridayView.setTextColor(Color.parseColor("#ffffff"));
			fridayView.setBackgroundColor(Color.parseColor("#bb1f2c"));
		}else if(day.equals("Saturday")){
			saturdayView.setTextColor(Color.parseColor("#ffffff"));
			saturdayView.setBackgroundColor(Color.parseColor("#bb1f2c"));
		}else if(day.equals("Sunday")){
			sundayView.setTextColor(Color.parseColor("#ffffff"));
			sundayView.setBackgroundColor(Color.parseColor("#bb1f2c"));
		}
	}

	/**
	 * Assign event handler to be passed needed events
	 */
	public void setEventHandler(EventHandler eventHandler){
		this.eventHandler = eventHandler;
	}

	/**
	 * This interface defines what events to be reported to
	 * the outside world
	 */
	public interface EventHandler{
		void onDayLongPress(Date date);
		void onDayPress(Date date);
	}


}
