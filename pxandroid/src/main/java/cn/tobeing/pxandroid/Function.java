package cn.tobeing.pxandroid;

/**
 * function to changed data
 * Created by sunzheng on 16/6/2.
 */
public interface Function<T,R> {
    R call(T data);
}
