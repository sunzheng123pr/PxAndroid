package cn.tobeing.pxandroid.proxy;

import android.os.Looper;

import java.lang.reflect.Proxy;

/**
 * Created by sunzheng on 16/6/1.
 */
public class UIProxy extends HandlerProxy{

    public UIProxy(Object subject) {
        super(subject, Looper.getMainLooper());
    }
    public static Object proxy(Object target) {
        return proxy(target,target.getClass().getInterfaces());
    }
    public static Object proxy(Object target, Class<?>... interfaces){
        UIProxy proxy=new UIProxy(target);
        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),interfaces,proxy);
    }
}
