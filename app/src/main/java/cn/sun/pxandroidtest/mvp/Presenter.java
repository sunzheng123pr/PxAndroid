package cn.sun.pxandroidtest.mvp;

import cn.sun.pxandroid.PxLog;
import cn.sun.pxandroid.proxy.UIProxy;

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
        //notice!!! this is work thread,耗时操作
        String messgae = model.getMessage();
        //next action will be invoked in UI thread
        iViews.showMessage(messgae);
    }
}
