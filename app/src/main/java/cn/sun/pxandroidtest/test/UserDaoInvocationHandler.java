package cn.sun.pxandroidtest.test;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserDaoInvocationHandler implements InvocationHandler {
    private Object mTarget;

    public UserDaoInvocationHandler(Object target) {
        mTarget = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始...");
        Object result = method.invoke(mTarget, args);
        System.out.println("结束...");
        return result;
    }
}
