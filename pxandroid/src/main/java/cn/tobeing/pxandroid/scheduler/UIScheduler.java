package cn.tobeing.pxandroid.scheduler;

import cn.tobeing.pxandroid.proxy.UIProxy;

/**
 * Created by sunzheng on 16/6/2.
 */
public class UIScheduler implements Scheduler {

    @Override
    public <T> T schedule(Object subject,Class<?> interfaces) {
        return (T) UIProxy.proxy(subject,interfaces);
    }
}
