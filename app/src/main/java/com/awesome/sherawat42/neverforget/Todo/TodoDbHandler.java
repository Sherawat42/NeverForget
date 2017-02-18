package com.awesome.sherawat42.neverforget.Todo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.playlog.internal.LogEvent;

import static android.content.ContentValues.TAG;

/**
 * Created by sherawat42 on 18/2/17.
 */

public class TodoDbHandler {
    /** This application's preferences label */
    private static final String PREFS_NAME = "com.awesome.sherawat42.neverforget.Todo.TodoDbHandler";
    /** This application's preferences */
    private static SharedPreferences storageSP;
    /** This application's storageSP editor*/
    private static SharedPreferences.Editor editor;
    /** Constructor takes an android.content.Context argument*/
    private Context ctx;

    private static String KEY_TODO_COUNT="countForAllTodosEverMade";
    private static int todoCount;

    private static void increseTodoCountBy1() {
        if(storageSP == null || storageSP == null){
            Log.e(TAG, "increseTodoCountBy1: There was an attempt to increase todo without initiailizing the class");
            return;
        }
        todoCount++;
        editor.putInt(KEY_TODO_COUNT,todoCount);
        editor.commit();
    }


    public static int getTodoCount() {
        return storageSP.getInt(KEY_TODO_COUNT,0);
    }

    public TodoDbHandler(Context ctx){
        if(storageSP == null){
            storageSP = ctx.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE );
        }
       /*
        * Get a SharedPreferences editor instance.
        * SharedPreferences ensures that updates are atomic
        * and non-concurrent
        */
        editor = storageSP.edit();
        this.ctx = ctx;
        setTodoCount();
    }

    private void setTodoCount() {
        todoCount = storageSP.getInt(KEY_TODO_COUNT,0);
    }


    /** The prefix for flattened user keys */
    public static final String KEY_PREFIX =
            "com.awesome.sherawat42.TodoDbHandler.KEY";

    /** Method to return a unique key for any field belonging to a given object
     * @param id of the object
     * @param fieldKey of a particular field belonging to that object
     * @return key String uniquely identifying the object's field
     */
    private String getFieldKey(int id, String fieldKey) {
        return  KEY_PREFIX + id + "_" + fieldKey;
    }


    private static String KEY_cYear="cYear";
    private static String KEY_cMonth="cMonth";
    private static String KEY_cDay="cDay";
    private static String KEY_cHour="cHour";
    private static String KEY_cMin="cMin";
    private static String KEY_rYear="rYear";
    private static String KEY_rMonth="rMonth";
    private static String KEY_rDay="rDay";
    private static String KEY_rHour="rHour";
    private static String KEY_rMin="rMin";
    private static String KEY_TITLE="title";
    private static String KEY_NOTE="note";
    private static String KEY_SOFT_DELETE="softDeleted?";



    /** Store or Update */
    public void setTodo(Todo todo){
        if(todo == null){
            Log.e(TAG, "setTodo: Error todo was null !!");
        }

        int id = todo.getId();

        editor.putInt(getFieldKey(id,KEY_cYear),todo.getcYear());
        editor.putInt(getFieldKey(id,KEY_cMonth),todo.getcMonth());
        editor.putInt(getFieldKey(id,KEY_cDay),todo.getcDay());
        editor.putInt(getFieldKey(id,KEY_cHour),todo.getcHour());
        editor.putInt(getFieldKey(id,KEY_cMin),todo.getcMinute());
        editor.putInt(getFieldKey(id,KEY_rYear),todo.getrYear());
        editor.putInt(getFieldKey(id,KEY_rMonth),todo.getrMonth());
        editor.putInt(getFieldKey(id,KEY_rDay),todo.getrDay());
        editor.putInt(getFieldKey(id,KEY_rHour),todo.getrHour());
        editor.putInt(getFieldKey(id,KEY_rMin),todo.getrMinute());
        editor.putString(getFieldKey(id,KEY_TITLE),todo.getTitle());
        editor.putString(getFieldKey(id,KEY_NOTE),todo.getNote());
        editor.putBoolean(getFieldKey(id,KEY_SOFT_DELETE),false);


        editor.commit();
        increseTodoCountBy1();
    }

//    Retrieve todo
    public Todo getTodo(int id){
        if(storageSP.getBoolean(getFieldKey(id,KEY_SOFT_DELETE),true) == true){
            Toast.makeText(ctx,"tring to get null todo",Toast.LENGTH_SHORT).show();
            return null;
        }
        String title = storageSP.getString(getFieldKey(id,KEY_TITLE),"");
        String note = storageSP.getString(getFieldKey(id,KEY_NOTE),"");
        int[] remindTimeArr=new int[5];
        int[] creationTimeArr=new int[5];
        creationTimeArr[0] = storageSP.getInt(getFieldKey(id,KEY_cYear),-1);
        creationTimeArr[1] = storageSP.getInt(getFieldKey(id,KEY_cMonth),-1);
        creationTimeArr[2] = storageSP.getInt(getFieldKey(id,KEY_cDay),-1);
        creationTimeArr[3] = storageSP.getInt(getFieldKey(id,KEY_cHour),-1);
        creationTimeArr[4] = storageSP.getInt(getFieldKey(id,KEY_cMin),-1);

        remindTimeArr[0] = storageSP.getInt(getFieldKey(id,KEY_rYear),-1);
        remindTimeArr[1] = storageSP.getInt(getFieldKey(id,KEY_rMonth),-1);
        remindTimeArr[2] = storageSP.getInt(getFieldKey(id,KEY_rDay),-1);
        remindTimeArr[3] = storageSP.getInt(getFieldKey(id,KEY_rHour),-1);
        remindTimeArr[4] = storageSP.getInt(getFieldKey(id,KEY_rMin),-1);

        return new Todo(remindTimeArr,creationTimeArr,title,note);
    }

//    delete todo
    public void softDeleteTodo(Todo todo){
        if(todo == null){
            Toast.makeText(ctx,"tring to delete null todo",Toast.LENGTH_SHORT).show();
            return;
        }
        int id = todo.getId();
        editor.putBoolean(getFieldKey(id,KEY_SOFT_DELETE),true);
        editor.commit();
    }
}