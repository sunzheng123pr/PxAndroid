package cn.tobeing.pxandroid.handler;

import android.os.Handler;
import android.os.Message;

import cn.tobeing.pxandroid.handler.recyclable.Recyclable;

/**
 * Created by sunzheng on 16/6/24.
 */
public class RecyclableHandler extends ProxyHandler {

    protected Recyclable recyclable;

    protected RecyclableHandler(Handler handler, Recyclable recyclable) {
        super(handler);
        this.recyclable = recyclable;
    }

    @Override
    public void handleMessage(Message msg) {
        if (recyclable != null && !recyclable.isRecyle()) {
            super.handleMessage(msg);
        }
    }

    /**
     * method to recyle this handler
     */
    public void release() {
        try {
            this.removeCallbacksAndMessages(null);
            getLooper().quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
