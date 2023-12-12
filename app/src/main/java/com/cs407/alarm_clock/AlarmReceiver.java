package com.cs407.alarm_clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cs407.alarm_clock.Ring.RingService;
import com.cs407.alarm_clock.server.LockServer;
import com.cs407.alarm_clock.utils.ScreenTool;

// define receiver
public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive: Ring..:" + intent.getStringExtra("TITLE"));

        startAlarmService(context, intent);

    }

    private void startAlarmService(Context context, Intent intent) {
        Intent intentService = new Intent(context, RingService.class);
        intentService.putExtra("TITLE", intent.getStringExtra("TITLE"));
        context.startForegroundService(intentService);

        LockServer.showLock();

//        Intent intent1 = new Intent(context, RingActivity.class);
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent1.putExtra("TITLE", intent.getStringExtra("TITLE"));
//        context.startActivity(intent1);
    }
}