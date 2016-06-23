package cn.tobeing.pxandroid.scheduler;

/**
 * Created by sunzheng on 16/6/2.
 */
public class Schedulers {

    public static Scheduler UI=new UIScheduler();

    public static Scheduler IO=new WorkScheduler();
}
