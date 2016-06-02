package cn.tobeing.pxandroid.proxy;

import android.os.HandlerThread;
import android.os.Looper;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sunzheng on 16/6/1.
 */
public class WorkProxy extends HandlerProxy{

    private static AtomicInteger count=new AtomicInteger();

    public WorkProxy(Object subject){
        super(subject,newWorkLooper(subject));
    }
    private static Looper newWorkLooper(Object subject){
        HandlerThread handlerThread=new HandlerThread(
                "WorkProxy."+count.getAndIncrement()+"."+subject.getClass().getName());
        handlerThread.start();
        return handlerThread.getLooper();
    }
    public static Object proxy(Object target) {
        return proxy(target,target.getClass().getInterfaces());
    }
    public static Object proxy(Object target, Class<?>... interfaces){
        WorkProxy proxy=new WorkProxy(target);
        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),interfaces,proxy);
    }
}
