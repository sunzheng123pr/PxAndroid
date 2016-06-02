package cn.tobeing.pxandroid;

/**
 * Created by sunzheng on 16/6/2.
 */
public class TransferPx<T> extends Px<T>{

    private Function<Object,? super T> func;
    private Px<?> px;
    public TransferPx(Function<Object,? super T> func,Px<?> px){
        super();
        this.px=px;
        this.func =func;
    }
    @Override
    public void dispatcher() {
        for (Object object:px.datas){
            datas.add((T) func.call(object));
        }
        super.dispatcher();
    }
}
