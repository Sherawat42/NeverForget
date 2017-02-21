package com.awesome.sherawat42.neverforget.Todo;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.awesome.sherawat42.neverforget.MainActivity;
import com.awesome.sherawat42.neverforget.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddTodoActivity extends AppCompatActivity implements View.OnClickListener {

    Button datePickerBTN;
    Button timePickerBTN;
    Button submitBTN;
    EditText todoTitleEditText;
    EditText todoNoteEditText;
    private Calendar c;
    int mYear = -1;
    int mMonth = -1;
    int mDay = -1;
    int mHour = -1;
    int mMinute = -1;

    int dYear = -1;
    int dMonth = -1;
    int dDay = -1;
    int dHour = -1;
    int dMinute = -1;

    private static TodoDbHandler todoDbHandler;


    private static AlarmManager am;
    private static Intent alarmIntent;




    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        if(todoDbHandler == null){
            todoDbHandler = new TodoDbHandler(this);
        }

        setTitle("Add TODO");

        am = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        alarmIntent = new Intent(AddTodoActivity.this,UpdateTodo.class);


        c = Calendar.getInstance();
        datePickerBTN = (Button) findViewById(R.id.set_date);
        timePickerBTN = (Button) findViewById(R.id.set_time);
        submitBTN = (Button) findViewById(R.id.add_todo);
        todoTitleEditText = (EditText) findViewById(R.id.todo_title);
        todoNoteEditText = (EditText) findViewById(R.id.todo_note);

        datePickerBTN.setOnClickListener(this);
        timePickerBTN.setOnClickListener(this);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
//                int x = dYear*600000+dMonth*45000+dDay*1500+dHour*60+dMinute;
//                int y = c.get(Calendar.YEAR)*600000 + c.get(Calendar.MONTH)*45000 + c.get(Calendar.DAY_OF_MONTH)*1500 + c.get(Calendar.HOUR_OF_DAY)*60 + c.get(Calendar.MINUTE);
                if (todoTitleEditText.getText().length() == 0) {
                    AlertDialog.Builder b = new AlertDialog.Builder(AddTodoActivity.this);
                    b.setTitle("There was an error");
                    View v1 = getLayoutInflater().inflate(R.layout.basic_dialogue_box_, null);
                    b.setView(v1);
                    TextView messageTV = (TextView) v1.findViewById(R.id.dialogTextView);
                    b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    messageTV.setText("There must be a title for the todo.");
                    AlertDialog aD = b.create();
                    aD.show();
                }else if (dYear == -1 || dMinute == -1) {
                    AlertDialog.Builder b = new AlertDialog.Builder(AddTodoActivity.this);
                    b.setTitle("There was an error");
                    View v1 = getLayoutInflater().inflate(R.layout.basic_dialogue_box_, null);
                    b.setView(v1);
                    TextView messageTV = (TextView) v1.findViewById(R.id.dialogTextView);
                    b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    messageTV.setText("Please choose date and time.");
                    AlertDialog aD = b.create();
                    aD.show();
                } else if (dYear*600000+dMonth*45000+dDay*1500+dHour*60+dMinute <= c.get(Calendar.YEAR)*600000 + c.get(Calendar.MONTH)*45000 + c.get(Calendar.DAY_OF_MONTH)*1500 + c.get(Calendar.HOUR_OF_DAY)*60 + c.get(Calendar.MINUTE)) {
                    AlertDialog.Builder b = new AlertDialog.Builder(AddTodoActivity.this);
                    b.setTitle("There was an error");
                    View v1 = getLayoutInflater().inflate(R.layout.basic_dialogue_box_, null);
                    b.setView(v1);
                    TextView messageTV = (TextView) v1.findViewById(R.id.dialogTextView);
                    b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    messageTV.setText("Time machine doesn't exist, so does the feature to make past time todo :P");
                    AlertDialog aD = b.create();
                    aD.show();
                }else{


                    int arr[] = new int[5];
                    arr[0] = dYear;
                    arr[1] = dMonth;
                    arr[2] = dDay;
                    arr[3] = dHour;
                    arr[4] = dMinute;
                    Todo newTodo;
                    if(todoNoteEditText.getText().length() == 0){
                        newTodo = new Todo(arr,todoTitleEditText.getText().toString());
                    }else{
                        newTodo = new Todo(arr,todoTitleEditText.getText().toString(),todoNoteEditText.getText().toString());
                    }
                    MainActivity.todoList.add(newTodo);
                    todoDbHandler.setTodo(newTodo);


                    setAlarm(AddTodoActivity.this, newTodo);


                    Intent i = new Intent();
                    setResult(MainActivity.RESULT_OK,i);
                    finish();
                }
            }
        });
    }

    public static void setAlarm(Context ctx, Todo todo){
        alarmIntent.putExtra(MainActivity.INDEX_STRING,todo.getId());
        //        PendingIntent operation = PendingIntent.getBroadcast(this,0,i,Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent.putExtra(MainActivity.INDEX_STRING,todo.getId());
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent alarmOperation = PendingIntent.getActivity(ctx , 0, alarmIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

        GregorianCalendar currentDay = new  GregorianCalendar (todo.getcYear(),todo.getcMonth(),todo.getcDay(),todo.getcHour(),todo.getcMinute(),0);
        GregorianCalendar alarmDay = new GregorianCalendar(todo.getrYear(),todo.getrMonth(),todo.getrDay(),todo.getrHour(),todo.getrMinute(),0);
        long millitime = alarmDay.getTimeInMillis()-currentDay.getTimeInMillis();
        am.set(AlarmManager. ELAPSED_REALTIME ,
                SystemClock.elapsedRealtime() + alarmDay.getTimeInMillis()-currentDay.getTimeInMillis(),
                alarmOperation);
        Toast.makeText(ctx,"alarm set: "+ (alarmDay.getTimeInMillis()-currentDay.getTimeInMillis()),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == datePickerBTN.getId()) {

            // Get Current Date
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            datePickerBTN.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            dYear = year;
                            dMonth = monthOfYear;
                            dDay = dayOfMonth;
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (v.getId() == timePickerBTN.getId()) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            timePickerBTN.setText(hourOfDay + ":" + minute);
                            dHour = hourOfDay;
                            dMinute = minute;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}