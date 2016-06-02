package cn.tobeing.pxandroidtest.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import cn.tobeing.pxandroid.proxy.WorkProxy;
import cn.tobeing.pxandroidtest.R;

/**
 * Created by sunzheng on 16/6/1.
 */
public class MVPTestActivity extends AppCompatActivity implements IViews{

    private IPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter= (IPresenter) WorkProxy.proxy(new Presenter(this,new Model()));
        presenter.loadMessage();
    }

    @Override
    public void showMessage(String message) {
        Log.d("suntest","IViews.showMessage."+Thread.currentThread());
        ((TextView)findViewById(R.id.tvTest)).setText(message);
    }

}
