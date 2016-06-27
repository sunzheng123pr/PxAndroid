package cn.tobeing.pxandroid.proxy;

import java.lang.reflect.Proxy;

import cn.tobeing.pxandroid.MyLog;
import cn.tobeing.pxandroid.proxy.InvokeRecord;
import cn.tobeing.pxandroid.proxy.WorkProxy;

/**
 * Created by sunzheng on 16/6/1.
 */
public class OnceWorkProxy extends WorkProxy {

    public OnceWorkProxy(Object subject) {
        super(subject);
    }

    @Override
    protected void handleInvoke(InvokeRecord record) {
        super.handleInvoke(record);
        mLooper.quit();
    }

    public static Object proxy(Object target) {
        return proxy(target, target.getClass().getInterfaces());
    }

    public static Object proxy(Object target, Class<?>... interfaces) {
        OnceWorkProxy proxy = new OnceWorkProxy(target);
        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(), interfaces, proxy);
    }
}
