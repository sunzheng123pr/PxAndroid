package cn.sun.pxandroid.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * Created by sunzheng on 16/5/31.
 */
public class CollectionProxy {

    public static Object proxy(Collection<?> collection, Class<?>... interfaces) {
        CollectionInvocationH invocationH = new CollectionInvocationH(collection, null);
        return Proxy.newProxyInstance(invocationH.getClass().getClassLoader(), interfaces, invocationH);
    }

    public static Object proxy(Collection<?> collection, Strategy strategy, Class<?>... interfaces) {
        CollectionInvocationH invocationH = new CollectionInvocationH(collection, strategy);
        return Proxy.newProxyInstance(invocationH.getClass().getClassLoader(), interfaces, invocationH);
    }

    public static class CollectionInvocationH implements InvocationHandler {

        private Collection<?> subSubjects;
        private Strategy mStrategy;

        public CollectionInvocationH(Collection<?> subSubjects, Strategy strategy) {
            this.subSubjects = subSubjects;
            this.mStrategy = strategy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            synchronized (subSubjects) {
                for (Object object : subSubjects) {
                    Object result = method.invoke(object, args);
                    if (mStrategy == Strategy.FINISH_WHEN_TRUE) {
                        if (result == Boolean.TRUE) {
                            return result;
                        }
                    }
                }
            }
            return null;
        }
    }

    enum Strategy {
        FINISH_WHEN_TRUE
    }
}
