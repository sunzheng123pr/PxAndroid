package cn.tobeing.pxandroid;

/**
 * Created by sunzheng on 16/6/2.
 */
public class TransferPx<T> extends Px<T> {

    private Function<Object, ? super T> func;
    private Px<?> px;

    public TransferPx(Function<Object, ? super T> func, Px<?> px) {
        super();
        this.px = px;
        this.func = func;
    }

    @Override
    protected int getCounts() {
        return px.getCounts();
    }
    @Override
    public void onDispatcher(Object object) {
        super.onDispatcher(func.call(object));
    }
}
