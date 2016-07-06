package cn.tobeing.pxandroidtest.invoke;

import java.lang.reflect.Proxy;

import cn.tobeing.pxandroid.MyLog;

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
        MyLog.d("suntest", "helloWorld");
    }
}
