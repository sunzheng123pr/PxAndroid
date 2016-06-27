package cn.tobeing.pxandroid.scheduler;

import cn.tobeing.pxandroid.proxy.CountWorkProxy;

/**
 * Created by sunzheng on 16/6/27.
 */
public class CountWorkScheduler implements Scheduler {
    private int count;

    public CountWorkScheduler(int count) {
        this.count = count;
    }

    @Override
    public <T> T schedule(Object subject, Class<?> interfaces) {
        return (T) CountWorkProxy.proxy(subject,count,interfaces);
    }
}
