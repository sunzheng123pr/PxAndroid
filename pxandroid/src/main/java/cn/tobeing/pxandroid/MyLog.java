package cn.tobeing.pxandroid;

import android.provider.Settings;
import android.util.Log;

/**
 * Created by sunzheng on 16/6/24.
 */
public class MyLog {
    private static final String TAG = "px-tag";

    private static final boolean DEBUG = true;

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, message);
        }
    }
}
