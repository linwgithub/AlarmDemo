package com.pollysoft.alarmdemo.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.pollysoft.alarmdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setAlarm(View view) {
        Toast.makeText(this, "alarm", Toast.LENGTH_SHORT).show();

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        long curTime = System.currentTimeMillis();
        curTime += 5000;
        Intent i = new Intent("com.pollysoft.alarmdemo.service.MyService");
        PendingIntent sender = PendingIntent.getService(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, curTime, sender);
    }
}
