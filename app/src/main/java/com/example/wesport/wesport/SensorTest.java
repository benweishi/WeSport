package com.example.wesport.wesport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SensorTest extends AppCompatActivity implements SensorEventListener {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;
    private SensorManager mSensorManager;
    private Sensor mAcc;
    private double maxAcc;
    private float[] pVector = {0,0,0};
    private double pv;
    private double maxDiff;
    private double bias = 25;
    private double scale = 50;
    private int distance = 0;
    private boolean Throughen = false;
    private int speed = 0;
    TextView TVDistant;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

        }
    };
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sencor_test);




        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

        TVDistant = (TextView) findViewById(R.id.textViewDistant);
        maxAcc = 0;
        maxDiff = 0;
        pv = 0;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.
        float[] vector = {event.values[0],event.values[1],event.values[2]};
        float[] vectorDiff = {vector[0]-pVector[0],vector[1]-pVector[1],vector[2]-pVector[2]};
        double v = vectorDiff[0]*vectorDiff[0]+vectorDiff[1]*vectorDiff[1]+vectorDiff[2]*vectorDiff[2];
        v = Math.sqrt(v);
        if (v > maxAcc) {
            maxAcc = v;
        }
        double diff = Math.abs(pv-v);
        if (diff > maxDiff) {
            maxDiff = diff;
        }
        if (maxAcc+maxDiff*2>35 && !Throughen){
            speed = (int)(maxAcc+maxDiff)/2;
            fly((int)((maxAcc+maxDiff - bias)*scale));
            Throughen = true;
        }
        pv=v;
        pVector = vector;
        // Do something with this sensor value.
    }

    private void fly(int score) {
        distance = 0;
        Timer stop = new Timer();
        final Timer fly = new Timer();
        fly.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                distance += speed;
                mHandler.obtainMessage(1).sendToTarget();
            }
        },0, 50);
        stop.schedule(new TimerTask() {
            @Override
            public void run() {
                fly.cancel();
            }
        }, score);
    }
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            TVDistant.setText(String.valueOf(distance));
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void reset(View view) {
        maxAcc = 0;
        maxDiff = 0;
        Throughen = false;
        TVDistant.setText("0");
    }
}
