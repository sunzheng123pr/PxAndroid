package scheduler;

/**
 * Created by sunzheng on 16/6/2.
 */
public interface Scheduler {
    <T> T schedule(Object subject,Class<?> interfaces);
}
