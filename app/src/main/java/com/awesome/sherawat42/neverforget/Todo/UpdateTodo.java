package com.awesome.sherawat42.neverforget.Todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.awesome.sherawat42.neverforget.MainActivity;
import com.awesome.sherawat42.neverforget.R;

public class UpdateTodo extends AppCompatActivity {


    Todo todo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);


        Intent i = getIntent();
        todo = MainActivity.todoList.get(i.getIntExtra(MainActivity.INDEX_STRING,0));
    }
}
