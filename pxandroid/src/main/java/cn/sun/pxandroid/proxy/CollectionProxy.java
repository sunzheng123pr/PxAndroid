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
        CollectionInvocationH invocationH = new CollectionInvocationH(collection);
        return Proxy.newProxyInstance(invocationH.getClass().getClassLoader(), interfaces, invocationH);
    }

    public static class CollectionInvocationH implements InvocationHandler {

        private Collection<?> subSubjects;

        public CollectionInvocationH(Collection<?> subSubjects) {
            this.subSubjects = subSubjects;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Object object : subSubjects) {
                method.invoke(object, args);
            }
            return null;
        }
    }
}
