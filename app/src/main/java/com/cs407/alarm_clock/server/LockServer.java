package com.cs407.alarm_clock.server;

import android.app.Service;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.IBinder;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.cs407.alarm_clock.R;
import com.cs407.alarm_clock.utils.ScreenTool;

public class LockServer extends Service {

    public static WindowManager mWManager;
    public static WindowManager.LayoutParams mWMParams;
    public static View rootLayout;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initWindowParams();
        initView();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private void initView() {
        rootLayout = LayoutInflater.from(this).inflate(R.layout.activity_puzzle_initialize, null, false);
    }

    public void initWindowParams() {
        mWManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWMParams = new WindowManager.LayoutParams();
        mWMParams.flags = (
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mWMParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mWMParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        Display display = mWManager.getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        mWMParams.width = point.x;
        mWMParams.height = point.y;
    }

    public static void showLock(){
        if (rootLayout != null && rootLayout.getRootView().getWindowToken() == null){
            ScreenTool.lightOn(rootLayout.getContext());
            mWManager.addView(rootLayout.getRootView(), mWMParams);
        }
    }

    public static void displayView(){
        if (rootLayout != null){
            mWManager.removeView(rootLayout.getRootView());
        }
    }
}
