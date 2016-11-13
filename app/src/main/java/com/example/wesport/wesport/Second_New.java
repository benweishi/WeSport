package com.example.wesport.wesport;


import android.app.AlarmManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.provider.AlarmClock;
import android.widget.Toast;



public class Second_New extends AppCompatActivity {
    private static final String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__new);
    }
    public void newPitch(View view) {
        Intent intent = new Intent(this, SensorTest.class);
        //EditText playerName = (EditText) findViewById(R.id.editText_PlayerName);
        //String name = playerName.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, name);
        startActivity(intent);
    }

}