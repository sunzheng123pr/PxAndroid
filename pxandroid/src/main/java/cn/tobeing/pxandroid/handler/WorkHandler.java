package cn.tobeing.pxandroid.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Created by sunzheng on 16/6/27.
 */
public class WorkHandler extends Handler {

    public WorkHandler() {
        this("WorkHandler");
    }

    public WorkHandler(String name) {
        super(newWorkLooper(name));
    }

    private static Looper newWorkLooper(String name) {
        HandlerThread thread = new HandlerThread(name);
        thread.start();
        return thread.getLooper();
    }
}
