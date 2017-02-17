package com.awesome.sherawat42.neverforget;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by sherawat42 on 12/2/17.
 */

public class Todo {

    private String title;
    private String note;
    private SimpleDateFormat sdf;
    private Calendar c;
    int cYear;
    int cMonth;
    int cDay;
    int cHour;
    int cMinute;
    int rYear;
    int rMonth;
    int rDay;
    int rHour;
    int rMinute;

    public Todo(int[] timeArr, String title, String note){
        this.title = title;
        c = Calendar.getInstance();
        this.note = note;
        setRemindTime(timeArr[0],timeArr[1],timeArr[2],timeArr[3],timeArr[4]);
    }

    public Todo(int[] timeArr, String title){
        this.title = title;
        c = Calendar.getInstance();
        setCreationTime();
        setRemindTime(timeArr[0],timeArr[1],timeArr[2],timeArr[3],timeArr[4]);
    }
    public void setCreationTime(){
        cYear = c.get(Calendar.YEAR);
        cMonth = c.get(Calendar.MONTH);
        cDay = c.get(Calendar.DAY_OF_MONTH);
        cHour = c.get(Calendar.HOUR_OF_DAY);
        cMinute = c.get(Calendar.MINUTE);
    }
    public void setRemindTime(int year, int month, int day, int hour, int minute){
        rYear = year;
        rMonth = month;
        rDay = day;
        rHour = hour;
        rMinute = minute;
    }
}
