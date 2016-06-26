package cn.tobeing.pxandroid.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Created by sunzheng on 16/6/27.
 */
public class UIHadnler extends Handler {

    public UIHadnler() {
        super(Looper.getMainLooper());
    }
}
