package com.example.baseapp.base;


import com.example.baseapp.net.ApiObserver;
import com.example.baseapp.net.api.ApiMethods;
import com.example.baseapp.net.api.RxApiManager;

/**
 * Created by Administrator on 2016/9/13.
 */
public abstract class BasePresenter<V extends BaseView> {
    /**
     * 绑定的view
     */
    protected V mView;


    protected ApiMethods apiMethods;
    private RxApiManager rxApiManager = RxApiManager.getsInstance();

    /**
     * 绑定view，一般在初始化中调用该方法
     */

    public void attachView(V mvpView) {
        this.mView = mvpView;
        start();
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
     * 预先处理的东西--初始化网络工具类
     */
    protected abstract void start();

    /**
     * 取消指定的请求
     *
     * @param tag 请求标签
     */
    public void cancleRequest(Object tag) {
        rxApiManager.cancel(tag);
    }

}
