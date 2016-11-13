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
        Button button3;
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activity_second__new);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getBaseContext(),"You click on second button", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sendMessage(View view) {
        Toast.makeText(getBaseContext(),"You click on first button", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Second_New.class);
        EditText editText = (EditText) findViewById(R.id.button3);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



}