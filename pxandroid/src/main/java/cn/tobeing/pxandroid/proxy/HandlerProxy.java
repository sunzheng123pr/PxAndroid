package cn.tobeing.pxandroid.proxy;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.tobeing.pxandroid.MyLog;

/**
 * method will be called in another mHandler
 * <p/>
 * Created by sunzheng on 16/5/30.
 */
public class HandlerProxy implements InvocationHandler {

    private static final String TAG = "HandlerProxy";

    protected Looper mLooper;

    private Handler mHandler;

    protected Object subject;

    public HandlerProxy(Object subject, Looper looper) {
        this.subject = subject;
        mHandler = new H(looper);
        mLooper = looper;
    }

    private static final int MSG_INVOKE_METHOD = 0x001;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long time2 = System.nanoTime() - MyLog.lasttime;
        MyLog.d("suntest", "timecost2=" + time2);
        MyLog.d(TAG, "invoke" + method.getName() + ".thread=" + Thread.currentThread().getName());
        if (!method.getReturnType().equals(Void.TYPE)) {
            throw new IllegalArgumentException("only void method is support in mHandler dispatcher." + method.getReturnType());
        }
        MyLog.d(TAG, "send invokemethod to another thread " + method.getName());

        mHandler.obtainMessage(MSG_INVOKE_METHOD, InvokeRecord.obtain(subject, method, args)).sendToTarget();

        return null;
    }

    public Looper getLooper() {
        return mLooper;
    }

    protected class H extends Handler {

        public H(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            MyLog.printMethod();
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_INVOKE_METHOD: {
                    MyLog.d(TAG, "handleMessage.MSG_INVOKE_METHOD." + Thread.currentThread().getName());
                    handleInvoke((InvokeRecord) msg.obj);
                }
                break;
            }
        }
    }

    /**
     * method called in work handle
     */
    protected void handleInvoke(InvokeRecord record) {
        record.invoke();
    }

    public static Object proxy(Object target, Looper looper) {
        return proxy(target, looper, target.getClass().getInterfaces());
    }

    public static Object proxy(Object target, Looper looper, Class<?>... interfaces) {
        HandlerProxy proxy = new HandlerProxy(target, looper);
        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(), interfaces, proxy);
    }
}
