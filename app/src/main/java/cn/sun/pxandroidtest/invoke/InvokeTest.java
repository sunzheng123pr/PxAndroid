package cn.sun.pxandroidtest.invoke;

import java.lang.reflect.Proxy;

import cn.sun.pxandroid.PxLog;

/**
 * Created by sunzheng on 16/6/28.
 */
public class InvokeTest implements TestInterface {
    public static TestInterface newInstance() {
        return (TestInterface) Proxy.newProxyInstance(InvokeTest.class.getClassLoader(),
                new Class<?>[]{TestInterface.class}, new TestProxy(new InvokeTest()));
    }

    @Override
    public void helloWorld() {
        PxLog.d("suntest", "helloWorld");
    }
}
