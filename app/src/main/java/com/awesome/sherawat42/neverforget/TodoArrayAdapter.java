package com.awesome.sherawat42.neverforget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sherawat42 on 13/2/17.
 */

public class TodoArrayAdapter extends ArrayAdapter<Todo> {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param objects  The objects to represent in the ListView.
     */
    ArrayList<Todo> todoArrayList;
    Context mContext;
    public TodoArrayAdapter(Context context, ArrayList todoArrayList) {
        super(context, 0, todoArrayList);
        mContext = context;
        this.todoArrayList = todoArrayList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(mContext,R.layout.todo_item_list_view,null);
        }



        TextView todoStringTV = (TextView)convertView.findViewById(R.id.todoStringTV);
        TextView creationTimeTV = (TextView)convertView.findViewById(R.id.creationTimeTV);
        TextView remindTimeTV = (TextView)convertView.findViewById(R.id.remindTimeTV);
        Todo todo = todoArrayList.get(position);
        todoStringTV.setText(todo.getMessage());


        todoStringTV.setText(todoArrayList.get(position).getMessage());
        creationTimeTV.setText("Created at "+todoArrayList.get(position).getCreationTime());
        remindTimeTV.setText("remind time: " + "functionality to be added");
        remindTimeTV.setText("remind time: " + "functionality to be added");



//        TextView numTV = (TextView) convertView.findViewById(R.id.todoStringTV);
//        TextView str = (TextView) convertView.findViewById(R.id.remindTimeTV);
//        numTV.setText(""+5);
//        str.setText("random");
        return convertView;
    }



//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView == null){
//            convertView = View.inflate(mContext,R.layout.todo_item_list_view,null);
//        }
//        TextView todoStringTV = (TextView)convertView.findViewById(R.id.todoStringTV);
//        TextView creationTimeTV = (TextView)convertView.findViewById(R.id.creationTimeTV);
//        TextView remindTimeTV = (TextView)convertView.findViewById(R.id.remindTimeTV);
//        Todo todo = todoArrayList.get(position);
//        todoStringTV.setText(todo.getMessage());
//
//
//        todoStringTV.setText(todoArrayList.get(position).getMessage());
//        creationTimeTV.setText("Created at "+todoArrayList.get(position).getCreationTime());
//        remindTimeTV.setText("remind time: " + "functionality to be added");
//        remindTimeTV.setText("remind time: " + "functionality to be added");
//        return convertView;
//    }

//    @Nullable
//    @Override
//    public Object getItem(int position) {
//        return todoArrayList.get(position);
//    }

    @Override
    public int getCount() {
        return todoArrayList.size();
    }
}