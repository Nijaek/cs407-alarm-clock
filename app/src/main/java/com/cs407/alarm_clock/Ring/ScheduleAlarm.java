package com.cs407.alarm_clock.Ring;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

import com.cs407.alarm_clock.AlarmReceiver;

import java.util.Calendar;

@android.support.annotation.RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class ScheduleAlarm {
    AlarmManager alarmManager =
            (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    private Object context;
    private int requestId;
    PendingIntent pendingIntent;
    if (pendingIntent != null && alarmManager != null)

    {
        alarmManager.cancel(pendingIntent);
        // Hopefully your alarm will have a lower frequency than this!
        AlarmManager alarmMgr = null;
        PendingIntent alarmIntent;
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
                AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) context.toString();
        Intent intent = new Intent((Context) context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast((Context) context, 0, intent, 0);

        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        60 * 1000, alarmIntent);
        // Set the alarm to start at approximately 2:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);

            // With setInexactRepeating(), you have to use one of the AlarmManager interval
            // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        // If the alarm has been set, cancel it.
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }
        class SampleBootReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                    // Set the alarm here.
                }
            }
        }