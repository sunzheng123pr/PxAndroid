package cn.tobeing.pxandroid.handler.recyclable;

import android.app.Fragment;

import java.lang.ref.WeakReference;

import cn.tobeing.pxandroid.handler.RecyclableHandler;

/**
 * Created by sunzheng on 16/6/24.
 */
public class FragmentRecyclable implements Recyclable {
    WeakReference<Fragment> fragmentWeakReference;

    public FragmentRecyclable(Fragment fragment) {
        fragmentWeakReference = new WeakReference<Fragment>(fragment);
    }

    @Override
    public boolean isRecyle() {
        Fragment fragment = fragmentWeakReference.get();
        return fragment == null || fragment.isDetached() || fragment.isRemoving();
    }
}
