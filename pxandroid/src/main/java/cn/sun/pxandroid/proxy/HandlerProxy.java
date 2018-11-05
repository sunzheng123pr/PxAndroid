package cn.sun.pxandroid.proxy;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * method will be called in another mHandler
 * <p/>
 * Created by sunzheng on 16/5/30.
 */
public class HandlerProxy {
    public static Object proxy(Object target, Looper looper, Class<?>... interfaces) {
        PostInvocationH postInvocationH = new PostInvocationH(target, looper);
        return Proxy.newProxyInstance(postInvocationH.getClass().getClassLoader(), interfaces, postInvocationH);
    }

    public static class PostInvocationH implements InvocationHandler {
        protected Looper mLooper;
        private Handler mHandler;
        protected Object target;

        public PostInvocationH(Object target, Looper looper) {
            this.target = target;
            mHandler = new H(looper);
            mLooper = looper;
        }

        @Override
        public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
            if (!method.getReturnType().equals(Void.TYPE)) {
                throw new IllegalArgumentException("only void method is support in mHandler dispatcher." + method.getReturnType());
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        method.invoke(target, args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }

        protected class H extends Handler {

            public H(Looper looper) {
                super(looper);
            }

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_INVOKE_METHOD: {
                        handleInvoke((InvokeRecord) msg.obj);
                    }
                    break;
                }
            }
        }

        /**
         * method called in io handle
         */
        protected void handleInvoke(InvokeRecord record) {
            record.invoke();
        }
    }

    /**
     * Created by sunzheng on 16/5/30.
     */
    public static class InvokeRecord {

        public Object proxy;

        public Method method;

        public Object[] args;

        public InvokeRecord(Object proxy, Method method, Object[] args) {
            this.proxy = proxy;
            this.method = method;
            this.args = args;
        }

        public static InvokeRecord obtain(Object proxy, Method method, Object[] args) {
            return new InvokeRecord(proxy, method, args);
        }

        public void invoke() {
            try {
                method.invoke(proxy, args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
