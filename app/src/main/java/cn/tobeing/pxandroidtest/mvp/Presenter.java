package cn.tobeing.pxandroidtest.mvp;

import android.os.Looper;
import android.util.Log;

import junit.framework.Test;

import cn.tobeing.pxandroid.MyLog;
import cn.tobeing.pxandroid.proxy.UIProxy;
import cn.tobeing.pxandroidtest.TestUtil;

/**
 * Created by sunzheng on 16/6/1.
 */
public class Presenter implements IPresenter {
    private IViews iViews;
    private IModel model;

    public Presenter(IViews views, IModel model) {
        //notice a UI proxy will be returen;
        this.iViews = (IViews) UIProxy.proxy(views);
        this.model = model;
    }

    @Override
    public void loadMessage() {
        MyLog.d("suntest", "Presenter.loadMessage." + Thread.currentThread());
        //notice this is work thread,耗时操作
        String messgae = model.getMessage();
        //next action will be invoked in UI thread
        iViews.showMessage(messgae);
    }
}
