package com.cs407.alarm_clock.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

public class FloatPerUtils {
    public static boolean isFloatWindowSettingsOn(Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(context);
    }
}