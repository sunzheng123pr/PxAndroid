package cn.tobeing.pxandroidtest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.UUID;

import cn.tobeing.pxandroid.Action;
import cn.tobeing.pxandroid.Function;
import cn.tobeing.pxandroid.Px;
import cn.tobeing.pxandroidtest.mvp.MVPTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnProxy).setOnClickListener(this);

    }
    public static void test(){
        Px.just("hello","world","sunzheng").io().map(new Function<String,String>() {
            @Override
            public String call(String String) {
                Log.d("suntest","Function1."+Thread.currentThread());
//                throw new IllegalArgumentException("what");
                return String+".call1.";
            }
        }).ui().map(new Function<String, String>() {
            @Override
            public String call(String data) {
                Log.d("suntest","Function2."+Thread.currentThread());
                return data+".call2";
            }
        }).io().subscribe(new Action<String>() {
            @Override
            public void call(String data) {
                Log.d("suntest",".subscribe.data="+data+Thread.currentThread());
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnProxy:{
//                test();
                startActivity(new Intent(this, MVPTestActivity.class));
            }
            break;
        }
    }
}
