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




public class Second_New extends AppCompatActivity {
    private static final String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Second_New.layout.activity_second__new);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, Second_New.class);
        EditText editText = (EditText) findViewById(R.id.button3);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



}