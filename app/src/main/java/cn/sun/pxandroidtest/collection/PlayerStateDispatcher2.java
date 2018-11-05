package cn.sun.pxandroidtest.collection;

import java.util.ArrayList;
import java.util.List;

import cn.sun.pxandroid.proxy.CollectionProxy;

/**
 * Created by sunzheng on 16/6/28.
 */
public class PlayerStateDispatcher2 implements IPlayerStateDispatcher {

    private List<PlayerStateListener> playerStateListeners = new ArrayList<>();

    private PlayerStateListener listenerProxy = (PlayerStateListener) CollectionProxy.proxy(playerStateListeners, PlayerStateListener.class);
    @Override
    public void dispatcherStart() {
        listenerProxy.onStart();
    }
    @Override
    public void dispatcherParpered() {
        listenerProxy.onParpered();
    }
    @Override
    public void dispatcherPause() {
        listenerProxy.onPause();
    }

    @Override
    public void dispatcherStop() {
        listenerProxy.onStop();
    }

    @Override
    public void dispatcherError(int errorCode) {
        listenerProxy.onError(errorCode);
    }

    @Override
    public void dispatcherComplection() {
        listenerProxy.onComplection();
    }

    @Override
    public void addPlayerStateListener(PlayerStateListener playerStateListener) {
        synchronized (playerStateListeners) {
            playerStateListeners.add(playerStateListener);
        }
    }

    @Override
    public void removePlayerStateListener(PlayerStateListener playerStateListener) {
        synchronized (playerStateListeners) {
            playerStateListeners.remove(playerStateListener);
        }
    }
}
