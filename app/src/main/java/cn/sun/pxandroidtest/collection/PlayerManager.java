package cn.sun.pxandroidtest.collection;

import cn.sun.pxandroid.PxLog;

/**
 * Created by sunzheng on 16/6/28.
 */
public class PlayerManager {

    private static PlayerManager instance = new PlayerManager();

    public static PlayerManager getInstance() {
        return instance;
    }

    private PlayerStateListener myListener = new PlayerStateListener() {
        @Override
        public void onStart() {
            PxLog.d("suntest", "onStart." + Thread.currentThread());
        }

        @Override
        public void onParpered() {
            PxLog.d("suntest", "onParpered." + Thread.currentThread());
        }

        @Override
        public void onPause() {
            PxLog.d("suntest", "onPause." + Thread.currentThread());
        }

        @Override
        public void onStop() {
            PxLog.d("suntest", "onStop." + Thread.currentThread());
        }

        @Override
        public void onError(int errorCode) {
            PxLog.d("suntest", "onError." + Thread.currentThread());
        }

        @Override
        public void onComplection() {
            PxLog.d("suntest", "onComplection." + Thread.currentThread());
        }
    };
    private PlayerStateListener myListener2 = new PlayerStateListener() {
        @Override
        public void onStart() {
            PxLog.d("suntest", "onStart2." + Thread.currentThread());
        }

        @Override
        public void onParpered() {
            PxLog.d("suntest", "onParpered2." + Thread.currentThread());
        }

        @Override
        public void onPause() {
            PxLog.d("suntest", "onPause2." + Thread.currentThread());
        }

        @Override
        public void onStop() {
            PxLog.d("suntest", "onStop2." + Thread.currentThread());
        }

        @Override
        public void onError(int errorCode) {
            PxLog.d("suntest", "onError2." + Thread.currentThread());
        }

        @Override
        public void onComplection() {
            PxLog.d("suntest", "onComplection2." + Thread.currentThread());
        }
    };
    public void test() {
        PxLog.d("suntest","一般写法回调");
        //一般写法
        IPlayerStateDispatcher dispatcher1 = new PlayerStateDispatcher();
        dispatcher1.addPlayerStateListener(myListener);
        dispatcher1.addPlayerStateListener(myListener2);
        dispatcher1.dispatcherStart();
        dispatcher1.dispatcherParpered();
        dispatcher1.dispatcherError(0);
        dispatcher1.dispatcherComplection();

        PxLog.d("suntest","集合代理回调");
        //集合代理
        IPlayerStateDispatcher dispatcher2 = new PlayerStateDispatcher2();
        dispatcher2.addPlayerStateListener(myListener);
        dispatcher2.addPlayerStateListener(myListener2);
        dispatcher2.dispatcherStart();
        dispatcher2.dispatcherParpered();
        dispatcher2.dispatcherError(0);
        dispatcher2.dispatcherComplection();


//        //集合代理+线程代理
//        MyLog.d("suntest","集合代理＋线程代理回调");
//        IPlayerStateDispatcher dispatcher3 = (IPlayerStateDispatcher) WorkProxy.proxy(new PlayerStateDispatcher2());
//        dispatcher3.addPlayerStateListener(myListener);
//        dispatcher3.addPlayerStateListener(myListener2);
//        dispatcher3.dispatcherStart();
//        dispatcher3.dispatcherParpered();
//        dispatcher3.dispatcherError(0);
//        dispatcher3.dispatcherComplection();
    }
}
