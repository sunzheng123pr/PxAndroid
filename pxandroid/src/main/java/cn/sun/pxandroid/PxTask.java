package cn.sun.pxandroid;

public interface PxTask<T> {
    void onExecute();

    void onDispatch(T object);

}
