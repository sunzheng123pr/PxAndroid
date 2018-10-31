package cn.sun.pxandroid;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import cn.sun.pxandroid.proxy.HandlerProxy;
import cn.sun.pxandroid.proxy.NewThreadProxy;
import cn.sun.pxandroid.proxy.UIProxy;
import cn.sun.pxandroid.proxy.WorkProxy;

/**
 * Created by sunzheng on 16/6/1.
 */
public final class Px<T> implements PxTask<T> {
    private Collection<T> datas;
    private boolean isCanceled;
    private PxTask next;
    private PxTask first;
    private Function mFunction;
    private Action mAction;

    public Px(Collection<T> datas) {
        this.datas = datas;
        this.first = this;
    }

    /**
     * changed to ui thread
     */
    public Px<T> ui() {
        Px<T> nextTask = Px.just();
        this.next = (PxTask) UIProxy.proxy(nextTask);
        nextTask.first = this.first;
        return nextTask;
    }

    /**
     * change to io thread
     * 每一个Px行为有一个work thread
     */
    public Px<T> io() {
        Px<T> nextTask = Px.just();
        this.next = (PxTask) WorkProxy.proxy(nextTask);
        nextTask.first = this.first;
        return nextTask;
    }

    /**
     * change to handle thread
     */
    public Px<T> handle(Handler handler) {
        Px<T> nextTask = Px.just();
        this.next = (PxTask) HandlerProxy.proxy(nextTask, handler);
        nextTask.first = this.first;
        return nextTask;
    }

    /**
     * 切换到新的后台线程
     */
    public Px<T> newThread() {
        Px<T> nextTask = Px.just();
        this.next = (PxTask) NewThreadProxy.proxy(nextTask);
        nextTask.first = this.first;
        return nextTask;
    }

    public final <R> Px<R> map(Function<? super T, ? extends R> func) {
        Px<R> nextTask = Px.just();
        nextTask.first = this.first;
        this.next = nextTask;
        this.mFunction = func;
        return nextTask;
    }

    @Override
    public void onExecute() {
        for (T object : datas) {
            if (isCanceled) {
                return;
            }
            if (mFunction != null) {
                if (next != null) {
                    Object result = mFunction.call(this, object);
                    if (isCanceled) {
                        return;
                    }
                    next.onDispatch(result);
                }
            } else if (next != null) {
                next.onDispatch(object);
            } else {
                if (this.mAction != null) {
                    this.mAction.run(object);
                }
            }

        }
    }

    @Override
    public void onDispatch(T object) {
        datas = Arrays.asList(object);
        onExecute();
    }

    public final void execute(Action<T> action) {
        this.mAction = action;
        execute();
    }

    public final void execute() {
        first.onExecute();
    }

    public final void cancel() {
        isCanceled = true;
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
}
