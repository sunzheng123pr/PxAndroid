package cn.tobeing.pxandroid;

import android.provider.Settings;
import android.util.Log;

/**
 * Created by sunzheng on 16/6/24.
 */
public class MyLog {
    private static final String TAG = "px-tag";

    private static final boolean DEBUG = true;

    public static Long lasttime = 0L;

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void printMethod() {
        printMethod("");
    }

    public static void printMethod(String info) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String method = "";
        if (elements != null) {
            for (int i = 0; i < elements.length; i++) {
                if (!elements[i].getMethodName().equals("printMethod")) {
                    continue;
                }
                if (i < elements.length - 1) {
                    method = elements[i + 1].toString();
                }
            }
        }
        if (elements != null) {
            Long lastTime = lasttime;
            Long currentTime = System.nanoTime();
            if (lastTime == 0) {
                lastTime = currentTime;
            }
            d(TAG, "printMethod." + method + "." + info + "." + (currentTime - lastTime));
            lasttime = currentTime;
        }
    }

}
