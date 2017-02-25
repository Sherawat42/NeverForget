package com.awesome.sherawat42.neverforget.Todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class UpdateTodo extends AppCompatActivity implements View.OnClickListener {


    Todo todo;
    Button datePickerBTN;
    Button timePickerBTN;
    Button submitBTN;
    EditText todoTitleEditText;
    EditText todoNoteEditText;
    private Calendar c;

    int dYear = -1;
    int dMonth = -1;
    int dDay = -1;
    int dHour = -1;
    int dMinute = -1;

    int mYear = -1;
    int mMonth = -1;
    int mDay = -1;
    int mHour = -1;
    int mMinute = -1;

    private static TodoDbHandler todoDbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);



        setTitle("Update todo");
        Intent i = getIntent();

        if(todoDbHandler == null){
            todoDbHandler = new TodoDbHandler(this);
        }
        if(MainActivity.todoList == null){
            MainActivity.makeTodoListFromDB(todoDbHandler);
        }

        todo = MainActivity.todoList.get(i.getIntExtra(MainActivity.INDEX_STRING,0));

        c = Calendar.getInstance();
        datePickerBTN = (Button) findViewById(R.id.set_date);
        timePickerBTN = (Button) findViewById(R.id.set_time);
        submitBTN = (Button) findViewById(R.id.update_todo);
        todoTitleEditText = (EditText) findViewById(R.id.todo_title);
        todoNoteEditText = (EditText) findViewById(R.id.todo_note);


        datePickerBTN.setOnClickListener(this);
        timePickerBTN.setOnClickListener(this);

        todoTitleEditText.setText(todo.getTitle());
        todoTitleEditText.setSelection(todoTitleEditText.getText().length());
        todoNoteEditText.setText(todo.getNote());
        datePickerBTN.setText(todo.getrYear()+"/"+(todo.getrMonth()+1)+"/"+todo.getrDay());
        timePickerBTN.setText(todo.getrHour()+":"+todo.getrMinute());

        dYear = todo.getrYear();
        dMonth = todo.getrMonth();
        dDay = todo.getrDay();
        dHour = todo.getrHour();
        dMinute = todo.getrMinute();
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
//                int x = dYear*600000+dMonth*45000+dDay*1500+dHour*60+dMinute;
//                int y = c.get(Calendar.YEAR)*600000 + c.get(Calendar.MONTH)*45000 + c.get(Calendar.DAY_OF_MONTH)*1500 + c.get(Calendar.HOUR_OF_DAY)*60 + c.get(Calendar.MINUTE);
                if (todoTitleEditText.getText().length() == 0) {
                    AlertDialog.Builder b = new AlertDialog.Builder(UpdateTodo.this);
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
                }else if (dYear*600000+dMonth*45000+dDay*1500+dHour*60+dMinute <= c.get(Calendar.YEAR)*600000 + c.get(Calendar.MONTH)*45000 + c.get(Calendar.DAY_OF_MONTH)*1500 + c.get(Calendar.HOUR_OF_DAY)*60 + c.get(Calendar.MINUTE)) {
                    AlertDialog.Builder b = new AlertDialog.Builder(UpdateTodo.this);
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
                    todo.setRemindTime(arr);
                    todo.setTitle(todoTitleEditText.getText().toString());
                    todo.setNote(todoNoteEditText.getText().toString());
                    todoDbHandler.setTodo(todo);
                    Intent i = new Intent();
                    setResult(MainActivity.RESULT_OK,i);
                    AddTodoActivity.setAlarm(UpdateTodo.this, todo);
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.setClass(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_todo,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.deleteTodo){
            todoDbHandler.softDeleteTodo(todo);
            MainActivity.todoList.remove(todo);
            Intent i = new Intent();
            setResult(MainActivity.RESULT_OK,i);
            finish();
        }
        return true;
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
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
