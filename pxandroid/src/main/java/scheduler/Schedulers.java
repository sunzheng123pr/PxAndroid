package scheduler;

import cn.tobeing.pxandroid.proxy.OnceWorkProxy;

/**
 * Created by sunzheng on 16/6/2.
 */
public class Schedulers {

    public static Scheduler UI=new UIScheduler();

    public static Scheduler IO=new WorkScheduler();
}
