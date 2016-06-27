package cn.tobeing.pxandroidtest;

/**
 * Created by sunzheng on 16/6/28.
 */
public class TestUtil {
    public static void sleep(int times) {
        try {
            Thread.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
