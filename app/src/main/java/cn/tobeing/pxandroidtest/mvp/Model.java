package cn.tobeing.pxandroidtest.mvp;

import android.util.Log;

/**
 * Created by sunzheng on 16/6/1.
 */
public class Model implements IModel {

    @Override
    public String getMessage() {
        Log.d("suntest","Model.getMessage."+Thread.currentThread());
        return "modle getMessage onThread"+Thread.currentThread();
    }
}
