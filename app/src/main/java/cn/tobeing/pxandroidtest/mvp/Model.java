package cn.tobeing.pxandroidtest.mvp;

import android.util.Log;

import cn.tobeing.pxandroid.MyLog;

/**
 * Created by sunzheng on 16/6/1.
 */
public class Model implements IModel {

    @Override
    public String getMessage() {
        MyLog.d("suntest","Model.getMessage."+Thread.currentThread());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "modle getMessage onThread"+Thread.currentThread();
    }
}
