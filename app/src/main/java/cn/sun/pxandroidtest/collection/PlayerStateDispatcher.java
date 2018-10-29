package cn.sun.pxandroidtest.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunzheng on 16/6/28.
 */
public class PlayerStateDispatcher implements IPlayerStateDispatcher {
    private List<PlayerStateListener> playerStateListeners = new ArrayList<>();

    @Override
    public void dispatcherStart() {
        synchronized (playerStateListeners) {
            for (PlayerStateListener listener : playerStateListeners) {
                listener.onStart();
            }
        }
    }

    @Override
    public void dispatcherParpered() {
        synchronized (playerStateListeners) {
            for (PlayerStateListener listener : playerStateListeners) {
                listener.onParpered();
            }
        }
    }

    @Override
    public void dispatcherPause() {
        synchronized (playerStateListeners) {
            for (PlayerStateListener listener : playerStateListeners) {
                listener.onPause();
            }
        }
    }

    @Override
    public void dispatcherStop() {
        synchronized (playerStateListeners) {
            for (PlayerStateListener listener : playerStateListeners) {
                listener.onStop();
            }
        }
    }

    @Override
    public void dispatcherError(int errorCode) {
        synchronized (playerStateListeners) {
            for (PlayerStateListener listener : playerStateListeners) {
                listener.onError(errorCode);
            }
        }
    }

    @Override
    public void dispatcherComplection() {
        synchronized (playerStateListeners) {
            for (PlayerStateListener listener : playerStateListeners) {
                listener.onComplection();
            }
        }
    }

    @Override
    public void addPlayerStateListener(PlayerStateListener playerStateListener) {
        synchronized (playerStateListeners){
            playerStateListeners.add(playerStateListener);
        }
    }

    @Override
    public void removePlayerStateListener(PlayerStateListener playerStateListener) {
        synchronized (playerStateListeners){
            playerStateListeners.remove(playerStateListener);
        }
    }
}
