package com.awesome.sherawat42.neverforget;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by sherawat42 on 12/2/17.
 */

public class Todo {
    private SimpleDateFormat sdf;
    private Calendar c;

    public Todo(String message){
        this.message = message;
        c = Calendar.getInstance();
        sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
        creationTime = sdf.format(c.getTime());
        Log.i(TAG, "Todo: created at"+ creationTime);
    }
    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String creationTime;
    private String message;


}
