package cn.sun.pxandroidtest.mvp;

import java.util.UUID;

import cn.sun.pxandroid.PxLog;
import cn.sun.pxandroidtest.TestUtil;

/**
 * Created by sunzheng on 16/6/1.
 */
public class Model implements IModel {

    @Override
    public String getMessage() {
        PxLog.d("suntest", "Model.getMessage." + Thread.currentThread());
        TestUtil.sleep(500);
        return "new message:" + UUID.randomUUID();
    }
}
