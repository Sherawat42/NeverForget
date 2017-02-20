package com.awesome.sherawat42.neverforget;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.awesome.sherawat42.neverforget.Todo.AddTodoActivity;
import com.awesome.sherawat42.neverforget.Todo.Todo;
import com.awesome.sherawat42.neverforget.Todo.TodoDbHandler;
import com.awesome.sherawat42.neverforget.Todo.UpdateTodo;

import java.util.ArrayList;

// TODO (1) Add Support for change of time zone!!
// TODO (2) Add the feature of going to next field after filling one field using keyboard and hide the keyboard on clicking elsewhere in the activity

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Todo> todoList;
    private static int todoCount;
    private TodoDbHandler todoDbHandler;
    public final static String INDEX_STRING = "index_of_the_selected_todo_in_arraylist";

    private static final int ADD_NEW_TODO_REQUEST_CODE=0;
    private static final int UPDATE_TODO_REQUEST_CODE=1;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.putExtra(INDEX_STRING,position);
                i.setClass(MainActivity.this,UpdateTodo.class);
                startActivityForResult(i,UPDATE_TODO_REQUEST_CODE);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            /**
             * Callback method to be invoked when an item in this view has been
             * clicked and held.
             * <p>
             * Implementers can call getItemAtPosition(position) if they need to access
             * the data associated with the selected item.
             *
             * @param parent   The AbsListView where the click happened
             * @param view     The view within the AbsListView that was clicked
             * @param position The position of the view in the list
             * @param id       The row id of the item that was clicked
             * @return true if the callback consumed the long click, false otherwise
             */
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Done with this todo?");
                View v = getLayoutInflater().inflate(R.layout.basic_dialogue_box_,null);
                b.setView(v);
                b.setCancelable(true);
                TextView tv = (TextView) v.findViewById(R.id.dialogTextView);
                tv.setText("Delete todo?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todoDbHandler.softDeleteTodo(todoList.get(position));
                        todoList.remove(position);
                        todoArrayAdapter.notifyDataSetChanged();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });



                AlertDialog d = b.create();
                d.show();


                return true;
            }
        });

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
            startActivityForResult(i,ADD_NEW_TODO_REQUEST_CODE);
        }else if(id == R.id.call_developer){
            Intent i = new Intent();
            i.setAction(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:+919643691758");
            i.setData(uri);
            startActivity(i);
        } else if (id == R.id.email_developer) {
            Intent i=new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"manishsherawat1@gmail.com"});
            i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback");
            i.putExtra(android.content.Intent.EXTRA_TEXT, "Choose your feedback");
            i.setType("plain/text");
            if(i.resolveActivity(getPackageManager()) != null){
                startActivity(i);
            }else{
                Toast.makeText(MainActivity.this, "Unhandled, no email client found", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_NEW_TODO_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                todoArrayAdapter.notifyDataSetChanged();
                Toast.makeText(this,"Todo added successfully",Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == UPDATE_TODO_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){

                Toast.makeText(this,"Todo updated successfully",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
