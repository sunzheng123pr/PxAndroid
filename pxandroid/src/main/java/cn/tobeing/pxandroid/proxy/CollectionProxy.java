package cn.tobeing.pxandroid.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * Created by sunzheng on 16/5/31.
 */
public class CollectionProxy implements InvocationHandler {

    private Collection<?> subSubjects;

    public CollectionProxy(Collection<?> subSubjects) {
        this.subSubjects = subSubjects;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        for (Object object : subSubjects) {
            method.invoke(object, args);
        }
        return null;
    }

    public static Object proxy(Collection<?> collection, Class<?>... interfaces) {
        CollectionProxy proxy = new CollectionProxy(collection);
        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(), interfaces, proxy);
    }
}
