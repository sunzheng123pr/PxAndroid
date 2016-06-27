package cn.tobeing.pxandroid.scheduler;

/**
 * Created by sunzheng on 16/6/2.
 */
public class Schedulers {

    /**
     * method will be scheduled in ui thread
     */
    public static Scheduler UI = new UIScheduler();
    /**
     * echo action will be scheduled in a new io thread
     */
//    public static Scheduler IO = new OnceWorkScheduler();
}
