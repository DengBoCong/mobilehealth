package com.example.power.mobile_health.UI.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by Power on 2019/1/12.
 */

public class ScreenUtils {

    public ScreenUtils(){
        throw new UnsupportedOperationException("不兼容，无法执行操作");
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context){
        int statusHeight = -1;
        try{
            Class<?> clazz = Class.forName("com.android.internal.R$demin");
            Object ocject = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                            .get(ocject).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        }catch (Exception e){
            e.printStackTrace();
        }

        return statusHeight;
    }

    /**
     * 获取虚拟功能键高度
     * @param context
     * @return
     */

    public static int getVirtualBarHeight(Context context){
        int virtualHeight = 0;
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        DisplayMetrics displayMetrics1 = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics1);
        try{
            @SuppressWarnings("未知类型")
            Class clazz = Class.forName("android.view.Display");
            @SuppressWarnings("未检测到设备")
            Method method = clazz.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            virtualHeight = displayMetrics.heightPixels - displayMetrics1.heightPixels;
        }catch (Exception e){
            e.printStackTrace();
        }
        return virtualHeight;
    }

    public static int getVirtualBarHeight(Activity activity){
        int titleHeight = 0;
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusHeight = frame.top;
        titleHeight = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop() - statusHeight;
        return titleHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity){
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bitmap1 = null;
        bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, width, height);
        view.destroyDrawingCache();
        return bitmap1;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity){
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int screenWidht = getScreenWidth(activity);
        int screenHeight = getScreenHeight(activity);
        Bitmap bitmap1 = null;
        bitmap1 = Bitmap.createBitmap(bitmap, 0 , statusBarHeight , screenWidht, screenHeight - statusBarHeight);
        view.destroyDrawingCache();
        return bitmap1;
    }
}
