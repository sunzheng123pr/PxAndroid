package cn.tobeing.pxandroid.scheduler;

import cn.tobeing.pxandroid.proxy.OnceWorkProxy;

/**
 * Created by sunzheng on 16/6/2.
 */
public class WorkScheduler implements Scheduler{
    @Override
    public <T> T schedule(Object subject,Class<?> interfaces) {
        return (T) OnceWorkProxy.proxy(subject,interfaces);
    }
}
