package com.awesome.sherawat42.neverforget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


import java.util.ArrayList;

// TODO (1) Add Support for change of time zone!!

public class MainActivity extends AppCompatActivity {

    ArrayList<Todo> todoList;
    Todo testTodo = new Todo("testing todo message");
    TodoArrayAdapter todoArrayAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoList = new ArrayList<>();
        for(int i=0;i<20;i++){
            todoList.add(testTodo);
        }
        listView = (ListView)findViewById(R.id.todoListView);
        listView.setAdapter(todoArrayAdapter);
    }
}
