package com.awesome.sherawat42.neverforget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import com.awesome.sherawat42.neverforget.Todo.Todo;
import com.awesome.sherawat42.neverforget.Todo.TodoDbHandler;

import java.util.ArrayList;

// TODO (1) Add Support for change of time zone!!
// TODO (2) Add the feature of going to next field after filling one field using keyboard and hide the keyboard on clicking elsewhere in the activity

public class MainActivity extends AppCompatActivity {
    static int ADD_NEW_TODO;
    static ArrayList<Todo> todoList;
    private static int todoCount;
    private TodoDbHandler todoDbHandler;


    TodoArrayAdapter todoArrayAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoDbHandler = new TodoDbHandler(this);
        todoList = new ArrayList<>();
        this.todoCount = TodoDbHandler.getTodoCount();
        makeTodoListFromDB();
        listView = (ListView)findViewById(R.id.todoListView);
        todoArrayAdapter = new TodoArrayAdapter(this, todoList);
        listView.setAdapter(todoArrayAdapter);
//        ListView listView = (ListView) findViewById(R.id.expenseListView);
////        adapter = new ArrayAdapter<>(this,R.layout.list_item,R.id.listItemTextView,expenses);
//        adapter = new ExpenseAdapter(this,expenses);
//        listView.setAdapter(adapter);

    }

//    Gson gson = new Gson();
    private void makeTodoListFromDB(){
        this.todoCount = TodoDbHandler.getTodoCount();
        Todo todo;
//        for(int i=0; i<100;i++){
        for(int i=0; i<todoCount;i++){
            todo = todoDbHandler.getTodo(i);
            if(todo == null){
                return;
            }else{
                todoList.add(todo);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add){
            Intent i = new Intent();
            i.setClass(this,AddTodoActivity.class);
            startActivityForResult(i,ADD_NEW_TODO);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("testing", "onActivityResult: 1");
        if(requestCode == ADD_NEW_TODO){
            Log.d("testing", "onActivityResult: 2");
            if(resultCode == Activity.RESULT_OK){
                Log.d("testing", "onActivityResult: 3");
                todoArrayAdapter.notifyDataSetChanged();
                Toast.makeText(this,"Todo added successfully",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
