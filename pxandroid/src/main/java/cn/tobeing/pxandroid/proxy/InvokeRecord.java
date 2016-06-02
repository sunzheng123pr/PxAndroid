package cn.tobeing.pxandroid.proxy;

import java.lang.reflect.Method;

/**
 * Created by sunzheng on 16/5/30.
 */
public class InvokeRecord {

    public Object proxy;

    public Method method;

    public Object[] args;

    public InvokeRecord(Object proxy, Method method, Object[] args){
        this.proxy=proxy;
        this.method=method;
        this.args=args;
    }
    public static InvokeRecord obtain(Object proxy, Method method, Object[] args){
        return new InvokeRecord(proxy, method, args);
    }
    public void invoke(){
        try {
            method.invoke(proxy,args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
