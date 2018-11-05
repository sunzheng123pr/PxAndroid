package cn.sun.pxandroidtest.test;

import android.os.SystemClock;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodTraceInvocationHandler implements InvocationHandler {
    private Object mTarget;
    public MethodTraceInvocationHandler(Object target) {
        mTarget = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = SystemClock.elapsedRealtime();
        Object result = method.invoke(mTarget, args);
        long duration = SystemClock.elapsedRealtime() - startTime;
        Log.d("test",String.format("方法%s:耗时=%s", method.getName(), duration));
        return result;
    }
}
