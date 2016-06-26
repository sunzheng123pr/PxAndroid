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
        setContentView(R.layout.activity_main);

        IPresenter iPresenter = new Presenter(this, new Model());
        presenter = (IPresenter) WorkProxy.proxy(iPresenter);
    }

    @Override
    public void showMessage(String message) {
        ((TextView) findViewById(R.id.tvTest)).setText(message);
    }
}
