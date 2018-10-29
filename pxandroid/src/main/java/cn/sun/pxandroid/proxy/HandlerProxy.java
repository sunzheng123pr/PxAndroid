package cn.sun.pxandroid.proxy;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * method will be called in another mHandler
 * <p/>
 * Created by sunzheng on 16/5/30.
 */
public class HandlerProxy {
    public static Object proxy(Object target, Handler handler) {
        return proxy(target, handler.getLooper(), target.getClass().getInterfaces());
    }

    public static Object proxy(Object target, Handler handler, Class<?>... interfaces) {
        return proxy(target, handler.getLooper(), interfaces);
    }

    public static Object proxy(Object target, Looper looper) {
        return proxy(target, looper, target.getClass().getInterfaces());
    }

    public static Object proxy(Object target, Looper looper, Class<?>... interfaces) {
        PostInvocationH postInvocationH = new PostInvocationH(target, looper);
        return Proxy.newProxyInstance(postInvocationH.getClass().getClassLoader(), interfaces, postInvocationH);
    }


    public static class PostInvocationH implements InvocationHandler {
        private static final String TAG = "HandlerProxy";
        protected Looper mLooper;

        private Handler mHandler;

        protected Object subject;

        public PostInvocationH(Object subject, Looper looper) {
            this.subject = subject;
            mHandler = new H(looper);
            mLooper = looper;
        }

        private static final int MSG_INVOKE_METHOD = 0x001;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!method.getReturnType().equals(Void.TYPE)) {
                throw new IllegalArgumentException("only void method is support in mHandler dispatcher." + method.getReturnType());
            }
            mHandler.obtainMessage(MSG_INVOKE_METHOD, InvokeRecord.obtain(subject, method, args)).sendToTarget();

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
         * method called in work handle
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
