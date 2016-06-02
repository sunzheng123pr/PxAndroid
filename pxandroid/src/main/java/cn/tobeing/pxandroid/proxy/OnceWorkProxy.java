package cn.tobeing.pxandroid.proxy;

import cn.tobeing.pxandroid.proxy.InvokeRecord;
import cn.tobeing.pxandroid.proxy.WorkProxy;

/**
 * Created by sunzheng on 16/6/1.
 */
public class OnceWorkProxy extends WorkProxy{

    public OnceWorkProxy(Object subject) {
        super(subject);
    }

    @Override
    protected void handleInvoke(InvokeRecord record) {
        super.handleInvoke(record);
        mLooper.quit();
    }
}
