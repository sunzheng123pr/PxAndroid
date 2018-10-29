package cn.sun.pxandroid.proxy;

import android.os.Looper;

import java.lang.reflect.Proxy;

/**
 * Created by sunzheng on 16/6/1.
 */
public class UIProxy {
    public static Object proxy(Object target) {
        return proxy(target, target.getClass().getInterfaces());
    }

    public static Object proxy(Object target, Class<?>... interfaces) {
        return HandlerProxy.proxy(target, Looper.getMainLooper(), interfaces);
    }
}
