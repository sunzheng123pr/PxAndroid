package cn.sun.pxandroidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import cn.sun.pxandroid.Action;
import cn.sun.pxandroid.Function;
import cn.sun.pxandroid.PxLog;
import cn.sun.pxandroid.Px;
import cn.sun.pxandroidtest.collection.PlayerManager;
import cn.sun.pxandroidtest.invoke.InvokeTest;
import cn.sun.pxandroidtest.mvp.MVPTestActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnPxAndroid2).setOnClickListener(this);
        findViewById(R.id.btnMvpTest).setOnClickListener(this);
        findViewById(R.id.btnRxJava).setOnClickListener(this);
        findViewById(R.id.btnPxAndroid).setOnClickListener(this);
        findViewById(R.id.btnCollectionTest).setOnClickListener(this);
        findViewById(R.id.btnProxy).setOnClickListener(this);
    }

    private static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void testPxAndroid() {
        Px.just("hello", "world", "sunzheng").work().map(new Function<String, String>() {
            @Override
            public String call(Px<String> px, String data) {//io线程，每一个px行为有一个
                TestUtil.sleep(500);
                Log.d("suntest", "Function1." + data + "." + isMainThread());
                return data + ".call1.";
            }
        }).newThread().map(new Function<String, String>() {
            @Override
            public String call(Px<String> px, String data) {//全新的IO线程
                Log.d("suntest", "Function2." + data + "." + isMainThread());
                TestUtil.sleep(500);
                return data + ".call2";
            }
        }).newThread().map(new Function<String, String>() {
            @Override
            public String call(Px<String> px, String data) {//全新的IO线程
                Log.d("suntest", "Function3." + data + "." + isMainThread());
                TestUtil.sleep(500);
                return data + ".call3";
            }
        }).work().map(new Function<String, String>() {
            @Override
            public String call(Px<String> px, String data) {//线程池
                Log.d("suntest", "Function4." + data + "." + isMainThread());
                TestUtil.sleep(500);
                return data + ".call4";
            }
        }).ui().execute(new Action<String>() {
            @Override
            public void run(String Object) {
                Log.d("suntest", "Function5." + Object + "." + isMainThread());
            }
        });
    }

    private static void testPxAndroid2() {
        Px.just("one", "two", "three")
                .newThread().map(new Function<String, String>() {
            @Override
            public String call(Px<String> px, String s) {
                PxLog.d("suntest", "pxAndroid.Funct1." + s + "." + isMainThread());
                TestUtil.sleep(500);
                return s + ".Funct1.";
            }
        }).ui().execute(new Action<String>() {
            @Override
            public void run(String Object) {
                Log.d("suntest", "Function5." + Object + "." + isMainThread());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMvpTest: {
                startActivity(new Intent(this, MVPTestActivity.class));
            }
            break;
            case R.id.btnRxJava: {
                testRxJava();
            }
            break;
            case R.id.btnPxAndroid: {
                testPxAndroid();
            }
            break;
            case R.id.btnPxAndroid2: {
                testPxAndroid2();
            }
            break;
            case R.id.btnCollectionTest: {
                testCollectionProxy();
            }
            break;
            case R.id.btnProxy: {
                testProxy();
            }
            break;
        }
    }

    private void testProxy() {
        InvokeTest.newInstance().helloWorld();
    }

    private void testCollectionProxy() {
        PlayerManager.getInstance().test();
    }

    private void testRxJava() {
        Observable.just("one", "two", "three", "four", "five")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        PxLog.d("suntest", "rxJava.Funct1." + s + "." + isMainThread());
                        TestUtil.sleep(500);
                        return s + ".Funct1.";
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PxLog.d("suntest", "rxJava.subscribe." + s + "." + isMainThread());
                    }
                });
    }
}
