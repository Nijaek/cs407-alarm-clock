package com.cs407.alarm_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class ShakeScreen extends AppCompatActivity {
    public int progress;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_screen);
        progress = 0;
        TextView shakeText = (TextView) findViewById(R.id.shakeText);
        shakeText.setText(Integer.toString(progress) + "% Finished\nShake The Phone");

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                mAccelLast = mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt((double)(x * x + y * y + z * z));
                float delta = mAccelCurrent - mAccelLast;
                mAccel = mAccel * 0.9f + delta;
                if(mAccel > 12) {
                    if(progress < 100) {
                        progress += 10;
                        shakeText.setText(Integer.toString(progress) + "% finished\nShake The Phone!");
                    }
                    if(progress == 100) {
                        progress = 101;
                        shakeText.setText("Good Job!");
                        goToMain();
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(sensorEventListener, sensorShake, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void goToMain() {
        Intent intent = new Intent(this, MathGenerator.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }




}