package com.cs407.alarm_clock.utils;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.Context;
import android.util.Log;

public class WallpaperPreUtils {

    private static final String TAG = "WallpaperUtils";

    public static boolean isLiveWallpaperRunning(Context context, String clsName) {
        WallpaperManager instance = WallpaperManager.getInstance(context);
        WallpaperInfo wallpaperInfo = instance.getWallpaperInfo();
        if (wallpaperInfo != null) {
            String curPackName = wallpaperInfo.getPackageName();
            if (context.getPackageName().equals(curPackName) && wallpaperInfo.getServiceName().equals(clsName)) {
                Log.i(TAG,"Service is running：" + clsName);
                return true;
            }
        }
        Log.i(TAG,"Service is off: " + clsName);
        return false;
    }
}