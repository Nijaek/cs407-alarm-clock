package com.cs407.alarm_clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cs407.alarm_clock.Ring.AlarmBroadcastReceiver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmUtils {

    private static final String TAG = "AlarmUtils";

    // 添加非重复闹钟
    public static void addSingleAlarm(Context context, String time, Bundle bundle) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timestamp = date.getTime();
        // 创建Intent
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtras(bundle);
        PendingIntent pendingIntent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast(context, bundle.getInt("noticeId"), intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getBroadcast(context,  bundle.getInt("noticeId"), intent, PendingIntent.FLAG_ONE_SHOT);
        }
        // 获取AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // 设置非重复闹钟
        alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent);

        Log.d(TAG, "addSingleAlarm: 非重复闹钟已设置");
    }
    public static void addSingleAlarm(Context context, long time, Bundle bundle) {
        // 创建Intent
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtras(bundle);
        PendingIntent pendingIntent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast(context, (int) bundle.getInt("id"), intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getBroadcast(context,  bundle.getInt("id"), intent, PendingIntent.FLAG_ONE_SHOT);
        }
        // 获取AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // 设置非重复闹钟
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);

        Log.d(TAG, "addSingleAlarm: 非重复闹钟已设置");
    }

    // 取消闹钟
    public static void cancelAlarm(Context context, int requestCode) {
        // 创建Intent
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent ;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast(context,requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        }

        // 获取AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // 取消闹钟
        alarmManager.cancel(pendingIntent);

        Log.d(TAG, "cancelAlarm: 闹钟已取消");
    }


}
