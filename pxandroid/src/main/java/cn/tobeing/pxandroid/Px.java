package cn.tobeing.pxandroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import cn.tobeing.pxandroid.scheduler.Scheduler;
import cn.tobeing.pxandroid.scheduler.Schedulers;

/**
 * Created by sunzheng on 16/6/1.
 */
public class Px<T> implements Subscriber<T>,IDispatcher<T>{

    protected Collection<T> datas;
    protected Px(){
        this(new ArrayList<T>());
    }
    protected Px(Collection<T> datas){
        this.datas=datas;
        this.first=this;
    }
    protected IDispatcher dispatcher;

    protected IDispatcher first;

    private Scheduler scheduler;

    public Px<T> ui(){
        scheduler= Schedulers.UI;
        if(first==this){
            first=scheduler.schedule(first,IDispatcher.class);
        }
        return this;
    }

    public Px<T> io(){
        scheduler=Schedulers.IO;
        if(first==this){
            first=scheduler.schedule(first,IDispatcher.class);
        }
        return this;
    }

    protected <E> TransferPx<E> transfer(Function<? super T, ? extends E> func){
        TransferPx<E> transferPx=new TransferPx(func,this);
        if(scheduler!=null) {
            this.dispatcher = scheduler.schedule(transferPx,IDispatcher.class);
        }else{
            this.dispatcher = transferPx;
        }
        transferPx.first=this.first;
        return transferPx;
    }
    public static <T> Px<T> create(Collection<T> datas){
        return new Px<>(datas);
    }
    public static <T> Px<T> from(Collection<T> datas){
        return new Px<>(datas);
    }

    public static <T> Px<T> from(T[] array) {
        Collection<T> collection=new ArrayList<T>();
        if(array!=null){
            for (T t:array){
                collection.add(t);
            }
        }
        return create(collection);
    }

    public static <T> Px<T> just(final T value) {
        return create(Arrays.asList(value));
    }
    public static <T> Px<T> just(final T... value){
        return create(Arrays.asList(value));
    }
    public final <R> Px<R> map(Function<? super T, ? extends R> func) {
        return transfer(func);
    }
    protected  Action action;
    public void subscribe(Action<T> action){
        Log.d("suntest","subscribe");
        if(scheduler!=null){
            action=scheduler.schedule(action,Action.class);
        }
        this.action=action;
        first.dispatcher();
    }
    public void dispatcher(){
        if(dispatcher!=null) {
            dispatcher.dispatcher();
        }else {
            Log.d("suntest", "dispatcher");
            for (T data : datas) {
                action.call(data);
            }
        }
    }
}
