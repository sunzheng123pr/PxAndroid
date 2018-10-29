package cn.sun.pxandroid.proxy;

import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sunzheng on 16/6/1.
 */
public class NewThreadProxy {
    public static Object proxy(Object target) {
        return proxy(target, target.getClass().getInterfaces());
    }

    public static Object proxy(Object target, Class<?>... interfaces) {
        NewThreadInvocationH invocationH = new NewThreadInvocationH(target);
        return Proxy.newProxyInstance(invocationH.getClass().getClassLoader(), interfaces, invocationH);
    }

    public static class NewThreadInvocationH implements InvocationHandler {

        private Object mSubject;

        public NewThreadInvocationH(Object subject) {
            this.mSubject = subject;
        }

        @Override
        public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
            new Thread(new Runnable() {
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
            }).start();
            return null;
        }
    }
}
