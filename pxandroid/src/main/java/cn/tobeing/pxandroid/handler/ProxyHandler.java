package cn.tobeing.pxandroid.handler;

import android.os.Handler;
import android.os.Message;

/**
 * Created by sunzheng on 16/6/24.
 */
public class ProxyHandler extends Handler {

    protected Handler subject;

    protected ProxyHandler(Handler handler) {
        super(handler.getLooper());
        subject = handler;
    }

    @Override
    public void handleMessage(Message msg) {
        subject.handleMessage(msg);
    }
}
