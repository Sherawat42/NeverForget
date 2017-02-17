package com.awesome.sherawat42.neverforget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

// TODO (1) Add Support for change of time zone!!

public class MainActivity extends AppCompatActivity {
    static int ADD_NEW_TODO;
    static ArrayList<Todo> todoList;

    TodoArrayAdapter todoArrayAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.todoListView);
        todoList = new ArrayList<>();
        todoArrayAdapter = new TodoArrayAdapter(this, todoList);
        listView.setAdapter(todoArrayAdapter);
//        ListView listView = (ListView) findViewById(R.id.expenseListView);
////        adapter = new ArrayAdapter<>(this,R.layout.list_item,R.id.listItemTextView,expenses);
//        adapter = new ExpenseAdapter(this,expenses);
//        listView.setAdapter(adapter);
    }

//    Gson gson = new Gson();


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
            i.setClass(this,AddTodo.class);
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
