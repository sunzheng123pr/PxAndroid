package cn.sun.pxandroidtest.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Proxy {

    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,
                                          InvocationHandler invocationHandler)
            throws IllegalArgumentException {
        Exception cause = null;
        try {
            return getProxyClass(loader, interfaces).getConstructor(InvocationHandler.class).newInstance(interfaces);
        } catch (Exception e) {
            e.printStackTrace();
            cause = e;
        }
        AssertionError error = new AssertionError();
        error.initCause(cause);
        throw error;
    }

    public static Class<?> getProxyClass(ClassLoader loader, Class<?>... interfaces) {
        //具体逻辑代码省略，这里包含对generateProxy的调用，class的缓存等等
        return null;
    }

    private static native Class<?> generateProxy(String name, Class<?>[] interfaces,
                                                 ClassLoader loader, Method[] methods,
                                                 Class<?>[][] exceptions);
}
