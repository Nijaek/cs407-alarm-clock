package com.cs407.alarm_clock.utils;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class ScreenTool
{
    private static WakeLock wl = null;

    //get wakelock object
    @SuppressLint("InvalidWakeLockTag")
    private static void getWakeLock(Context context)
    {
        PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);	//get power manager

        //get PowerManager.WakeLock, last one is for test.
        wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
    }


    // lighten the screen
    @SuppressLint("Wakelock")
    @SuppressWarnings("deprecation")
    public static void lightOn(Context context)
    {
        getWakeLock(context);

        // screen is on
        wl.acquire();
    }

    // screen is off
    public static void lightOff(Context context)
    {
        if(wl==null) getWakeLock(context);
        if(wl != null) wl.release();
    }


    @SuppressWarnings("deprecation")
    private static KeyguardLock kl = null;

    // get KeyLock
    @SuppressWarnings("deprecation")
    private static void getKeyLock(Context context)
    {
        //get keyguard manager
        KeyguardManager km= (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
        kl = km.newKeyguardLock("unLock");
    }


    // lock the screen using the keyboard
    @SuppressWarnings("deprecation")
    public static void keyLock(Context context)
    {
        getKeyLock(context);

        kl.reenableKeyguard();
    }

    // unlock
    @SuppressWarnings("deprecation")
    public static void keyLockOff(Context context)
    {
        if(kl==null) getKeyLock(context);
        if(kl != null) kl.disableKeyguard();
    }

}