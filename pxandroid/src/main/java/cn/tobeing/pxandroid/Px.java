package cn.tobeing.pxandroid;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import cn.tobeing.pxandroid.handler.WorkHandler;
import cn.tobeing.pxandroid.scheduler.HandlerScheduler;
import cn.tobeing.pxandroid.scheduler.Scheduler;
import cn.tobeing.pxandroid.scheduler.Schedulers;
import cn.tobeing.pxandroid.scheduler.OnceWorkScheduler;

/**
 * Created by sunzheng on 16/6/1.
 */
public class Px<T> implements Subscriber<T>, IDispatcher<T> {
    private static final int MSG_FINISH_HANDLER = 0x0001;
    protected Collection<T> datas;
    protected HandlerScheduler ioScheduler;
    protected Px() {
        this(new ArrayList<T>());
    }

    protected Px(Collection<T> datas) {
        this.datas = datas;
        this.first = this;
    }

    private void initIOScheduler() {
        if (ioScheduler == null) {
            Handler handler = new WorkHandler(this.toString());
            ioScheduler = new HandlerScheduler(handler);
        }
    }

    protected IDispatcher dispatcher;

    protected IDispatcher first;

    protected Px firstPx;

    private Scheduler scheduler;

    public Px<T> ui() {
        scheduler = Schedulers.UI;
        if (first == this) {
            first = scheduler.schedule(first, IDispatcher.class);
        }
        return this;
    }

    /**
     *
     * */
    public Px<T> io() {
        scheduler = getScheduler();
        if (first == this) {
            first = scheduler.schedule(first, IDispatcher.class);
        }
        return this;
    }

    public Px<T> nio() {
        scheduler = Schedulers.IO;
        if (first == this) {
            first = scheduler.schedule(first, IDispatcher.class);
        }
        return this;
    }

    protected <E> TransferPx<E> transfer(Function<? super T, ? extends E> func) {
        TransferPx<E> transferPx = new TransferPx(func, this);
        if (scheduler != null) {
            this.dispatcher = scheduler.schedule(transferPx, IDispatcher.class);
        } else {
            this.dispatcher = transferPx;
        }
        transferPx.first = this.first;
        transferPx.ioScheduler = this.getScheduler();
        return transferPx;
    }

    public static <T> Px<T> create(Collection<T> datas) {
        return new Px<>(datas);
    }

    public static <T> Px<T> from(Collection<T> datas) {
        return new Px<>(datas);
    }

    public static <T> Px<T> from(T[] array) {
        Collection<T> collection = new ArrayList<T>();
        if (array != null) {
            for (T t : array) {
                collection.add(t);
            }
        }
        return create(collection);
    }

    public static <T> Px<T> just(final T value) {
        return create(Arrays.asList(value));
    }

    public static <T> Px<T> just(final T... value) {
        return create(Arrays.asList(value));
    }

    public final <R> Px<R> map(Function<? super T, ? extends R> func) {
        return transfer(func);
    }

    protected Action action;

    public void subscribe(Action<T> action) {
        Log.d("suntest", "subscribe");
        if (scheduler != null) {
            action = scheduler.schedule(action, Action.class);
        }
        this.action = action;
        first.dispatcher();
    }

    @Override
    public HandlerScheduler getScheduler() {
        initIOScheduler();
        return ioScheduler;
    }

    public void dispatcher() {
        if (dispatcher != null) {
            dispatcher.dispatcher();
        } else {
            Log.d("suntest", "dispatcher");
            for (T data : datas) {
                action.call(data);
            }
            if(ioScheduler!=null) {
                ioScheduler.release();
            }
        }
    }
}
