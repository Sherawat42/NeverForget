package com.awesome.sherawat42.neverforget.Todo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sherawat42 on 12/2/17.
 */

public class Todo {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String note;
    private SimpleDateFormat sdf;
    private Calendar c;
    private int cYear;
    private int cMonth;
    private int cDay;
    private int cHour;
    private int cMinute;
    private int rYear;
    private  int rMonth;
    private  int rDay;
    private int rHour;
    private int rMinute;

    private static int todoCount=0;
    private int id;


    public Todo(int[] remindTimeArr, String title, String note){
        this.title = title;
        c = Calendar.getInstance();
        this.note = note;
        setRemindTime(remindTimeArr);
        setCreationTime();

        id = todoCount;
        setTodoCount(getTodoCount()+1);
    }

    public Todo(int[] remindTimeArr, String title){
        this.title = title;
        c = Calendar.getInstance();
        setCreationTime();
        setRemindTime(remindTimeArr);
        id = todoCount;
        setTodoCount(getTodoCount()+1);
    }

    public Todo(int[] remindTimeArr,int[] creationTimeArr, String title, String note){
        this.title = title;
        c = Calendar.getInstance();
        this.note = note;
        setRemindTime(remindTimeArr);
        setCreationTime(creationTimeArr);
        id = todoCount;
        setTodoCount(getTodoCount()+1);
    }
    
    public void setCreationTime(){
        cYear = c.get(Calendar.YEAR);
        cMonth = c.get(Calendar.MONTH);
        cDay = c.get(Calendar.DAY_OF_MONTH);
        cHour = c.get(Calendar.HOUR_OF_DAY);
        cMinute = c.get(Calendar.MINUTE);
    }
    public void setCreationTime(int[] creationTimeArr){
        cYear = creationTimeArr[0];
        cMonth = creationTimeArr[1];
        cDay = creationTimeArr[2];
        cHour = creationTimeArr[3];
        cMinute = creationTimeArr[4];
    }

    public void setRemindTime(int[] remindTimeArr){
        rYear = remindTimeArr[0];
        rMonth = remindTimeArr[1];
        rDay = remindTimeArr[2];
        rHour = remindTimeArr[3];
        rMinute = remindTimeArr[4];
    }


    protected void finalize ()  {
        setTodoCount(getTodoCount()-1);
    }
    public String getNote() {
        return note;
    }

    public int getId() {
        return id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getcYear() {
        return cYear;
    }

    public void setcYear(int cYear) {
        this.cYear = cYear;
    }

    public int getcMonth() {
        return cMonth;
    }

    public void setcMonth(int cMonth) {
        this.cMonth = cMonth;
    }

    public int getcDay() {
        return cDay;
    }

    public void setcDay(int cDay) {
        this.cDay = cDay;
    }

    public int getcHour() {
        return cHour;
    }

    public void setcHour(int cHour) {
        this.cHour = cHour;
    }

    public int getcMinute() {
        return cMinute;
    }

    public void setcMinute(int cMinute) {
        this.cMinute = cMinute;
    }

    public int getrYear() {
        return rYear;
    }

    public void setrYear(int rYear) {
        this.rYear = rYear;
    }

    public int getrDay() {
        return rDay;
    }

    public void setrDay(int rDay) {
        this.rDay = rDay;
    }

    public int getrMonth() {
        return rMonth;
    }

    public void setrMonth(int rMonth) {
        this.rMonth = rMonth;
    }

    public int getrHour() {
        return rHour;
    }

    public void setrHour(int rHour) {
        this.rHour = rHour;
    }

    public int getrMinute() {
        return rMinute;
    }

    public void setrMinute(int rMinute) {
        this.rMinute = rMinute;
    }

    public static int getTodoCount() {
        return todoCount;
    }

    public static void setTodoCount(int todoCount) {
        Todo.todoCount = todoCount;
    }
}
