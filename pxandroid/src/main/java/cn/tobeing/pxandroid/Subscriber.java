package cn.tobeing.pxandroid;

import cn.tobeing.pxandroid.scheduler.HandlerScheduler;

/**
 * Created by sunzheng on 16/6/2.
 */
public interface Subscriber<T> {

    void subscribe(Action<T> action);

    HandlerScheduler getIOScheduler();
}
