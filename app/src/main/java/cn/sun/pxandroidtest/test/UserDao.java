package cn.sun.pxandroidtest.test;

/**
 * 接口实现
 * 目标对象
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
