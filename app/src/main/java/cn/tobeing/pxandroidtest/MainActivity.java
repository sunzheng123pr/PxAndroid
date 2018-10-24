package cn.tobeing.pxandroidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.tobeing.pxandroid.Action;
import cn.tobeing.pxandroid.Function;
import cn.tobeing.pxandroid.MyLog;
import cn.tobeing.pxandroid.Px;
import cn.tobeing.pxandroidtest.collection.PlayerManager;
import cn.tobeing.pxandroidtest.invoke.InvokeTest;
import cn.tobeing.pxandroidtest.mvp.MVPTestActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    public static void testPxAndroid() {
        Px.just("hello", "world", "sunzheng").work().map(new Function<String, String>() {
            @Override
            public String call(String data) {//io线程，每一个px行为有一个
                TestUtil.sleep(500);
                Log.d("suntest", "Function1." + data + "." + Thread.currentThread());
                return data + ".call1.";
            }
        }).newThread().map(new Function<String, String>() {
            @Override
            public String call(String data) {//全新的IO线程
                Log.d("suntest", "Function2." + data + "." + Thread.currentThread());
//                TestUtil.sleep(500);
                return data + ".call2";
            }
        }).newThread().map(new Function<String, String>() {
            @Override
            public String call(String data) {//全新的IO线程
                Log.d("suntest", "Function3." + data + "." + Thread.currentThread());
//                TestUtil.sleep(500);
                return data + ".call3";
            }
        }).newThread().map(new Function<String, String>() {
            @Override
            public String call(String data) {//全新的IO线程
                Log.d("suntest", "Function4." + data + "." + Thread.currentThread());
//                TestUtil.sleep(500);
                return data + ".call4";
            }
        }).ui().subscribe(new Action<String>() {
            @Override
            public void call(String data) {//UI线程
                Log.d("suntest", ".subscribe.data=" + data + Thread.currentThread());
            }
        });
    }
    private static void testPxAndroid2(){
        Px.just("one", "two", "three", "four", "five")
                .newThread().map(new Function<String, String>() {
            @Override
            public String call(String s) {
                MyLog.d("suntest", "pxAndroid.Funct1." + s + "." + Thread.currentThread().getName());
                TestUtil.sleep(500);
                return s + ".Funct1.";
            }
        }).ui().subscribe(new Action<String>() {
            @Override
            public void call(String s) {
                MyLog.d("suntest", "pxAndroid.subscribe." + s + "." + Thread.currentThread().getName());
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
            case R.id.btnPxAndroid:{
                testPxAndroid();
            }
            break;
            case R.id.btnPxAndroid2: {
                testPxAndroid2();
            }
            break;
            case R.id.btnCollectionTest:{
                testCollectionProxy();
            }
            break;
            case R.id.btnProxy:{
                testProxy();
            }
            break;
        }
    }
    private void testProxy(){
        InvokeTest.newInstance().helloWorld();
    }
    private void testCollectionProxy(){
        PlayerManager.getInstance().test();
    }
    private void testRxJava() {
        Observable.just("one", "two", "three", "four", "five")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        MyLog.d("suntest", "rxJava.Funct1." + s + "." + Thread.currentThread().getName());
                        TestUtil.sleep(500);
                        return s + ".Funct1.";
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        MyLog.d("suntest", "rxJava.subscribe." + s + "." + Thread.currentThread().getName());
                    }
                });
    }
}
