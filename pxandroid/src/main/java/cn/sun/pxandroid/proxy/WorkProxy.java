package cn.sun.pxandroid.proxy;

import android.os.HandlerThread;
import android.os.Looper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sunzheng on 16/6/1.
 */
public class WorkProxy {
    private static ExecutorService mExecutor = Executors.newFixedThreadPool(4);

    public static Object proxy(Object target) {
        return proxy(target, target.getClass().getInterfaces());
    }

    public static Object proxy(Object target, Class<?>... interfaces) {
        WorkInvocationH invocationH = new WorkInvocationH(target);
        return Proxy.newProxyInstance(invocationH.getClass().getClassLoader(), interfaces, invocationH);
    }

    public static class WorkInvocationH implements InvocationHandler {

        private Object mSubject;

        public WorkInvocationH(Object subject) {
            this.mSubject = subject;
        }

        @Override
        public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        method.invoke(mSubject, args);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }
    }
}
