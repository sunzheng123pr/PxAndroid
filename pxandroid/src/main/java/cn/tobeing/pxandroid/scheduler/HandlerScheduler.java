package cn.tobeing.pxandroid.scheduler;

import android.os.Handler;

import cn.tobeing.pxandroid.MyLog;
import cn.tobeing.pxandroid.proxy.HandlerProxy;
import cn.tobeing.pxandroid.proxy.OnceWorkProxy;
import cn.tobeing.pxandroid.proxy.WorkProxy;

/**
 * Created by sunzheng on 16/6/27.
 */
public class HandlerScheduler implements Scheduler {

    private Handler handler;

    private int count;

    public HandlerScheduler(Handler handler, int count) {
        this.handler = handler;
        this.count = count;
    }

    @Override
    public <T> T schedule(Object subject, Class<?> interfaces) {
        return (T) HandlerProxy.proxy(subject, handler.getLooper(), interfaces);
    }

    public void release() {
        count--;
        if (count != 0) {
            return;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MyLog.d("suntest", "HandlerScheduler.release." + handler.getLooper().getThread().getName());
                handler.getLooper().quit();
            }
        }, 200);
    }
}
