package cn.sun.pxandroidtest.collection;

/**
 * Created by sunzheng on 16/6/28.
 */
public interface PlayerStateListener {

    void onStart();

    void onParpered();

    void onPause();

    void onStop();

    void onError(int errorCode);

    void onComplection();
}
