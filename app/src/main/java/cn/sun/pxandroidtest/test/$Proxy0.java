package cn.sun.pxandroidtest.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class $Proxy0 implements IUserDao {
    private InvocationHandler mInvocationHandler;

    public $Proxy0(InvocationHandler invocationHandler) {
        mInvocationHandler = invocationHandler;
    }

    @Override
    public void save() {
        Method currentMethod = null;
        Object[] agrs = null;
        try {
            mInvocationHandler.invoke(this, currentMethod, agrs);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}