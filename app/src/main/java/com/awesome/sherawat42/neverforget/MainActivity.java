package com.awesome.sherawat42.neverforget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import java.util.ArrayList;

// TODO (1) Add Support for change of time zone!!
// TODO (2) Add the feature of going to next field after filling one field using keyboard and hide the keyboard on clicking elsewhere in the activity

public class MainActivity extends AppCompatActivity {

    ArrayList<Todo> todoList;
    Todo testTodo = new Todo("testing todo message");
    TodoArrayAdapter todoArrayAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.todoListView);
        todoList = new ArrayList<>();
        for(int i=0;i<20;i++){
            todoList.add(testTodo);
        }
        todoArrayAdapter = new TodoArrayAdapter(this, todoList);
        listView.setAdapter(todoArrayAdapter);
//        ListView listView = (ListView) findViewById(R.id.expenseListView);
////        adapter = new ArrayAdapter<>(this,R.layout.list_item,R.id.listItemTextView,expenses);
//        adapter = new ExpenseAdapter(this,expenses);
//        listView.setAdapter(adapter);
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
            
        }
        return true;
    }
}
