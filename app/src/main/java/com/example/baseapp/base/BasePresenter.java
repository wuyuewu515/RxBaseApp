package com.example.baseapp.base;


/**
 * Created by Administrator on 2016/9/13.
 */
public abstract class BasePresenter<V extends BaseView> {
    /**
     * 绑定的view
     */
    protected V mView;

    /**
     * 绑定view，一般在初始化中调用该方法
     */

    public void attachView(V mvpView) {
        this.mView = mvpView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */

    public void detachView() {
        this.mView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * presenter 和model层 绑定
     */
    protected abstract void bindPM();

}
