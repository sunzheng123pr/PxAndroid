package cn.tobeing.pxandroid;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import cn.tobeing.pxandroid.handler.WorkHandler;
import cn.tobeing.pxandroid.scheduler.CountWorkScheduler;
import cn.tobeing.pxandroid.scheduler.HandlerScheduler;
import cn.tobeing.pxandroid.scheduler.Scheduler;
import cn.tobeing.pxandroid.scheduler.Schedulers;

/**
 * Created by sunzheng on 16/6/1.
 */
public class Px<T> implements Subscriber<T>, IDispatcher<T> {
    protected Collection<T> datas;

    protected HandlerScheduler ioScheduler;

    protected CountWorkScheduler countWorkScheduler;


    protected Px() {
        this(new ArrayList<T>());
    }

    protected Px(Collection<T> datas) {
        this.datas = datas;
        this.first = this;
    }

    private CountWorkScheduler getCountWorkScheduler() {
        if (countWorkScheduler == null) {
            countWorkScheduler = new CountWorkScheduler(getCounts());
        }
        return countWorkScheduler;
    }

    protected int getCounts() {
        return datas == null ? 0 : datas.size();
    }

    protected IDispatcher dispatcher;

    protected IDispatcher first;

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
        scheduler = getIOScheduler();
        if (first == this) {
            first = scheduler.schedule(first, IDispatcher.class);
        }
        return this;
    }

    public Px<T> nio() {
        scheduler = getCountWorkScheduler();
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
        transferPx.ioScheduler = this.getIOScheduler();
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
    public HandlerScheduler getIOScheduler() {
        if (ioScheduler == null) {
            Handler handler = new WorkHandler(this.toString());
            ioScheduler = new HandlerScheduler(handler, getCounts());
        }
        return ioScheduler;
    }

    @Override
    public void onDispatcher(Object object) {
        if (dispatcher != null) {
            dispatcher.onDispatcher(object);
        } else {
            action.call(object);
            if (ioScheduler != null) {
                ioScheduler.release();
            }
        }
    }

    public void dispatcher() {
        Log.d("suntest", "dispatcher");
        for (T data : datas) {
            onDispatcher(data);
        }
    }
}
