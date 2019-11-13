package com.custum.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class DaySeparator extends FrameLayout {

    public DaySeparator(Context context) {
        super(context);
        init(null);
    }

    public DaySeparator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DaySeparator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.day_seprator_layout, this);


    }

}
