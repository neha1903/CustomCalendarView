package com.custum.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DayView extends FrameLayout {

    private TextView mTextHour;
    private LinearLayout daySepratorLayout;


    public DayView(Context context) {
        super(context);
        init(null);
    }

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_view_day, this);

        mTextHour = (TextView) findViewById(R.id.texthour);
        daySepratorLayout = (LinearLayout) findViewById(R.id.sepratorhr);
        setDaySepratorLayout();
    }

    public void setDaySepratorLayout(){
        DaySeparator daySeparator = new DaySeparator(getContext());
        daySepratorLayout.addView(daySeparator);
    }

    public void setText(String text) {
        mTextHour.setText(text);
    }
}
