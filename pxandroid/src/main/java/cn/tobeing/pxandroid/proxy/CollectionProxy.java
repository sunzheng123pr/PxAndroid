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

    private Object subject;

    public CollectionProxy(Object subject, Collection<?> subSubjects){
        this.subSubjects=subSubjects;
        this.subject=subject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        for (Object object:subSubjects){

            method.invoke(object,args);
        }
        return method.invoke(subject,args);
    }
    public static Object proxy(Object target, Collection<?> collection) {
        return proxy(target,collection,target.getClass().getInterfaces());
    }
    public static Object proxy(Object target, Collection<?> collection, Class<?>... interfaces){
        CollectionProxy proxy=new CollectionProxy(target,collection);
        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),interfaces,proxy);
    }
}
