package cn.tobeing.pxandroidtest.mvp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.tobeing.pxandroid.MyLog;
import cn.tobeing.pxandroid.proxy.WorkProxy;
import cn.tobeing.pxandroidtest.R;

/**
 * Created by sunzheng on 16/6/1.
 */
public class MVPTestActivity extends AppCompatActivity implements IViews {

    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.lasttime = System.nanoTime();
        setContentView(R.layout.activity_main);
        IPresenter iPresenter = new Presenter(this, new Model());
        presenter = (IPresenter) WorkProxy.proxy(iPresenter);

        MyLog.lasttime = System.nanoTime();
        iPresenter.loadMessage();

        MyLog.lasttime = System.nanoTime();
        presenter.loadMessage();
    }

    @Override
    public void showMessage(String message) {
        MyLog.printMethod();
        Log.d("suntest", "IViews.showMessage." + Thread.currentThread());
        ((TextView) findViewById(R.id.tvTest)).setText(message);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        long time1 = System.nanoTime() - MyLog.lasttime;
        MyLog.d("suntest", "timecost1=" + time1);
        MyLog.lasttime = System.nanoTime();
        super.setContentView(layoutResID);
    }
}
