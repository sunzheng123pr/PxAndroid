package cn.tobeing.pxandroid.handler.recyclable;

/**
 * Created by sunzheng on 16/6/24.
 */

import android.app.Activity;
import android.os.Build;

import java.lang.ref.WeakReference;

import cn.tobeing.pxandroid.handler.RecyclableHandler;

/**
 *
 * */
public class ActivityRecyclable implements Recyclable {
    WeakReference<Activity> fragmentWeakReference;

    public ActivityRecyclable(Activity activity) {
        fragmentWeakReference = new WeakReference<>(activity);
    }

    @Override
    public boolean isRecyle() {
        Activity activity = fragmentWeakReference.get();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return activity != null || activity.isDestroyed();
        }
        return activity == null || activity.isFinishing();
    }
}