package cn.sun.pxandroidtest.test;

import java.lang.reflect.Proxy;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * 测试类
 */
public class App {
    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);

        proxy.save();//执行的是代理的方法
    }


    public static void main2(String[] args) {
        //目标对象
        UserDao target = new UserDao();
        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoInvocationHandler handler
                = new UserDaoInvocationHandler(target);
        IUserDao proxy =
                (IUserDao) Proxy.newProxyInstance(
                        UserDao.class.getClassLoader()
                        , new Class[]{IUserDao.class}
                        , handler);

        proxy.save();//执行的是代理的方法
    }

    public static void main3(String[] args) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").build();
        //使用动态代理生成GitHubService实例
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos("octocat");
    }
}
