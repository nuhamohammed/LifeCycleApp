package com.mohammednuha.cycleapp.lifecycleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     Counter obj = new Counter();
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    public Button resetBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetBtn= findViewById(R.id.resetbtn);
        sp = getSharedPreferences("LC_APP", Context.MODE_PRIVATE);//PreferenceManager.getDefaultSharedPreferences(getApplicationContext())

        updateText(0, R.id.create, "create", false);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(0, R.id.create, "create", true);
                updateText(1, R.id.start, "start", true);
                updateText(2, R.id.resume, "resume", true);
                updateText(3, R.id.pause, "pause", true);
                updateText(4, R.id.stop, "stop", true);
                updateText(5, R.id.restart, "restart", true);
                updateText(6,  R.id.destroy, "destroy",true);
            }
        });
    }
    protected void onStart() {
        super.onStart();
        updateText(1, R.id.start, "start", false);
    }

    protected void onResume() {
        super.onResume();
        updateText(2, R.id.resume, "resume", false);
    }

    protected void onPause() {
        super.onPause();
        updateText(3, R.id.pause, "pause", false);
    }

    protected void onStop() {
        super.onStop();
        updateText(4, R.id.stop, "stop", false);
    }

    protected void onRestart() {
        super.onRestart();
        updateText(5, R.id.restart, "restart", false);
    }

    protected void onDestroy() {
        super.onDestroy();
        updateText(6,  R.id.destroy, "destroy",false);
    }

    public void updateText(int i, int id, String name, boolean reset){
        int count;
        editor = sp.edit();
        if(reset) {
            count = 0;
            obj.resetVal(i);
        }
        else {
            count = sp.getInt(name, 0) + 1;
            obj.increment(i);
        }
        editor.putInt(name, count);
        editor.apply();
        TextView myView= findViewById(id);
        myView.setText(name + ": " + obj.getVal(i) + " " + count);
    }
}
 class Counter extends AppCompatActivity{
     private int[]  method;
     public Counter(){
         method = new int[7];
     }
     public void resetVal(int i){
         method[i]=0;
     }
     public void increment(int i){
         method[i]++;
     }
     public int getVal(int i){
         return method[i];
     }
        }