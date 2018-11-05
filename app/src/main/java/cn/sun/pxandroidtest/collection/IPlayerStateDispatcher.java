package cn.sun.pxandroidtest.collection;

/**
 * Created by sunzheng on 16/6/28.
 */
public interface IPlayerStateDispatcher {
    void dispatcherStart();
    void dispatcherParpered();
    void dispatcherPause();
    void dispatcherStop();
    void dispatcherError(int errorCode);
    //部分方法省略...
    void dispatcherComplection();

    void addPlayerStateListener(PlayerStateListener playerStateListener);

    void removePlayerStateListener(PlayerStateListener playerStateListener);
}
