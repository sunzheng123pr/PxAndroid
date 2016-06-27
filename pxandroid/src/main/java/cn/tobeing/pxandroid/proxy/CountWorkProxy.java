package cn.tobeing.pxandroid.proxy;

import java.lang.reflect.Proxy;

import cn.tobeing.pxandroid.MyLog;

/**
 * work proxy that release after count times;
 * <p/>
 * Created by sunzheng on 16/6/27.
 */
public class CountWorkProxy extends WorkProxy {
    private int count;

    public CountWorkProxy(Object subject, int count) {
        super(subject);
        this.count = count;
        if (count < 1) {
            throw new IllegalArgumentException("count must be bigger than one");
        }
    }

    @Override
    protected void handleInvoke(InvokeRecord record) {
        super.handleInvoke(record);
        count--;
        if (count == 0) {
            MyLog.d("suntest","release.CountWorkProxy."+this.toString());
            mLooper.quit();
        }
    }

    public static Object proxy(Object target, int count) {
        return proxy(target, count, target.getClass().getInterfaces());
    }

    public static Object proxy(Object target, int count, Class<?>... interfaces) {
        CountWorkProxy proxy = new CountWorkProxy(target, count);
        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(), interfaces, proxy);
    }
}
