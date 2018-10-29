package cn.sun.pxandroid.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class NullableProxy {
    public static Object proxy(Object target, Class<?>... interfaces) {
        NullInvocationH invocationH = new NullInvocationH(target);
        return Proxy.newProxyInstance(invocationH.getClass().getClassLoader(), interfaces, invocationH);
    }

    public static class NullInvocationH implements InvocationHandler {

        private Object mSubject;

        public NullInvocationH(Object subject) {
            this.mSubject = subject;
        }

        @Override
        public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
            if (mSubject != null) {
                method.invoke(mSubject, args);
            }
            return null;
        }
    }
}
