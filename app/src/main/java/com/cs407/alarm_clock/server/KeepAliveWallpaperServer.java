package com.cs407.alarm_clock.server;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import androidx.activity.ComponentActivity;

public class KeepAliveWallpaperServer extends WallpaperService {

    @Override
    public Engine onCreateEngine() {
        return new KeepAliveEngine();
    }

    class KeepAliveEngine extends WallpaperService.Engine {
        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            if (visible){
                getCanvas(getSurfaceHolder(), new CanvasDrawCallback() {
                    @Override
                    public void drawContent(Canvas canvas) {
                        canvas.drawColor(Color.BLACK);
                    }
                });
            }
        }
    }

    private void getCanvas(SurfaceHolder holder, CanvasDrawCallback callback){
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if (canvas != null){
                callback.drawContent(canvas);
            }
        } finally {
            if (canvas != null){
                try {
                    holder.unlockCanvasAndPost(canvas);
                } catch (Throwable throwable){
                    throwable.printStackTrace();
                }
            }
        }
    }

    interface CanvasDrawCallback{
        void drawContent(Canvas canvas);
    }

    public static int REQUEST_SET_LIVE_WALLPAPER = 1001;

    /**
     * keep alive
     * @param context
     */
    public static void setKeepAliveWallpaper(ComponentActivity context) {
        Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context.getPackageName(), KeepAliveWallpaperServer.class.getName()));
        context.startActivityForResult(intent, REQUEST_SET_LIVE_WALLPAPER);
    }
}
