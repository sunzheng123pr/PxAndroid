package cn.tobeing.pxandroid;

/**
 * Created by sunzheng on 16/6/2.
 */
public interface Action<T> {
    void call(T data);
}
