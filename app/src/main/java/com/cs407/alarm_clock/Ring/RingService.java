package com.cs407.alarm_clock.Ring;

import static com.cs407.alarm_clock.Ring.AlarmBroadcastReceiver.TITLE;
import static com.cs407.alarm_clock.Ring.createNotification.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.cs407.alarm_clock.R;

public class RingService extends Service {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.ring);
        mediaPlayer.setLooping(true);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, RingService.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        String alarmTitle = String.format("%s Alarm", intent.getStringExtra(TITLE));
        Log.d("TAG", "onStartCommand: ");


        String CHANNEL_ONE_ID = "example";
        String CHANNEL_ONE_NAME = "Channel One";
        NotificationChannel notificationChannel = null;
        notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(notificationChannel);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(alarmTitle)
                .setContentText("Ring .. ")
                .setChannelId(CHANNEL_ONE_ID)
                .setSmallIcon(R.drawable.baseline_alarm_24)
                .setContentIntent(pendingIntent)
                .build();

        manager.notify((int) System.currentTimeMillis(),notification);
        mediaPlayer.start();

        long[] pattern = { 0, 100, 1000 };
        vibrator.vibrate(pattern, 0);

        startForeground(1, notification);

        return START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        vibrator.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

