package com.example.wesport.wesport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Activity_third extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Activity_Third);

        Intent intent = getIntent();
        String message = intent.getStringExtra(Second_New.message);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
    }

}
