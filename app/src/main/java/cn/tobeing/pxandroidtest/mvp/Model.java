package cn.tobeing.pxandroidtest.mvp;

import android.util.Log;

import java.util.UUID;

import cn.tobeing.pxandroid.MyLog;
import cn.tobeing.pxandroidtest.TestUtil;

/**
 * Created by sunzheng on 16/6/1.
 */
public class Model implements IModel {

    @Override
    public String getMessage() {
        MyLog.d("suntest", "Model.getMessage." + Thread.currentThread());
        TestUtil.sleep(500);
        return "new message:" + UUID.randomUUID();
    }
}
