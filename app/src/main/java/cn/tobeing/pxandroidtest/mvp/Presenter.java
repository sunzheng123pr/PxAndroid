package cn.tobeing.pxandroidtest.mvp;

import android.os.Looper;
import android.util.Log;

import cn.tobeing.pxandroid.MyLog;
import cn.tobeing.pxandroid.proxy.UIProxy;

/**
 * Created by sunzheng on 16/6/1.
 */
public class Presenter implements IPresenter {
    private IViews iViews;
    private IModel model;

    public Presenter(IViews views, IModel model) {
        this.iViews = (IViews) UIProxy.proxy(views);
        this.model = model;
    }

    @Override
    public void loadMessage() {
        String messgae = model.getMessage();
        iViews.showMessage(messgae);
    }
}
