package cn.sun.pxandroidtest.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.sun.pxandroid.PxLog;
import cn.sun.pxandroid.proxy.WorkProxy;
import cn.sun.pxandroidtest.R;

/**
 * Created by sunzheng on 16/6/1.
 */
public class MVPTestActivity extends Activity implements IViews, View.OnClickListener {

    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_test);
        findViewById(R.id.btnMvpTest).setOnClickListener(this);

        IPresenter iPresenter = new Presenter(this, new Model());
        presenter = (IPresenter) WorkProxy.proxy(iPresenter);
    }

    @Override
    public void showMessage(String message) {
        PxLog.d("suntest", "IViews.showMessage." + Thread.currentThread());
        ((TextView) findViewById(R.id.tvTest)).setText(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMvpTest: {
                //method will be invoke in work thread
                presenter.loadMessage();
            }
            break;
        }
    }
}
