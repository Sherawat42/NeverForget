package com.awesome.sherawat42.neverforget;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


import com.awesome.sherawat42.neverforget.Todo.Todo;

import java.util.Calendar;

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


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        setTitle("My title");


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
                AlertDialog.Builder b = new AlertDialog.Builder(AddTodoActivity.this);
                b.setTitle("There was an error");
                View v1 = getLayoutInflater().inflate(R.layout.add_todo_error_dialogue_box, null);
                b.setView(v1);
                TextView messageTV = (TextView) v1.findViewById(R.id.dialogTextView);
                b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                c = Calendar.getInstance();
                int x = dYear*600000+dMonth*45000+dDay*1500+dHour*60+dMinute;
                int y = c.get(Calendar.YEAR)*600000 + c.get(Calendar.MONTH)*45000 + c.get(Calendar.DAY_OF_MONTH)*1500 + c.get(Calendar.HOUR_OF_DAY)*60 + c.get(Calendar.MINUTE);
                if (todoTitleEditText.getText().length() == 0) {
                    messageTV.setText("There must be a title for the todo.");
                    AlertDialog aD = b.create();
                    aD.show();
                } else if (dYear*600000+dMonth*45000+dDay*1500+dHour*60+dMinute <= c.get(Calendar.YEAR)*600000 + c.get(Calendar.MONTH)*45000 + c.get(Calendar.DAY_OF_MONTH)*1500 + c.get(Calendar.HOUR_OF_DAY)*60 + c.get(Calendar.MINUTE)) {
                    messageTV.setText("Time machine doesn't exist, so does the feature to make past time todo :P");
                    AlertDialog aD = b.create();
                    aD.show();
                }

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
                    newTodo = new Todo(arr,todoTitleEditText.getText().toString(),todoTitleEditText.getText().toString());
                }
                MainActivity.todoList.add(newTodo);
//                final Gson gson = new Gson();
                Intent i = new Intent();
                setResult(MainActivity.RESULT_OK,i);
                finish();
            }
        });
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