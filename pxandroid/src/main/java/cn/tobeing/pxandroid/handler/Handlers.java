package cn.tobeing.pxandroid.handler;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Handler;

import java.lang.ref.WeakReference;

import cn.tobeing.pxandroid.handler.recyclable.ActivityRecyclable;
import cn.tobeing.pxandroid.handler.recyclable.FragmentRecyclable;

/**
 * Created by sunzheng on 16/6/24.
 */
public class Handlers {
    /**
     * return a new recycle handler
     */
    public static Handler recyclable(Handler handler, Fragment fragment) {
        return recyclableHandler(handler, fragment);
    }

    /**
     * return a new recyclable handler
     */
    public static Handler recyclable(Handler handler, Activity activity) {
        return recyclableHandler(handler, activity);
    }

    /**
     * return a new recyclable handler
     */
    public static RecyclableHandler recyclableHandler(Handler handler, Activity activity) {
        return new RecyclableHandler(handler, new ActivityRecyclable(activity));
    }

    /**
     * return a new recyclable handler
     */
    public static RecyclableHandler recyclableHandler(Handler handler, Fragment fragment) {
        return new RecyclableHandler(handler, new FragmentRecyclable(fragment));
    }

}
