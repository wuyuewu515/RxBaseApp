package com.example.baseapp.net.api;

import com.example.baseapp.base.BaseView;
import com.example.baseapp.bean.BeanData;
import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.net.RetrofitManager;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: Five_伍
 * @create: 2019/2/13
 * @Describe: 所有接口调用方法，相当于model层
 */
public class ApiMethods<V extends BaseView> {
    private WanAndroidService androidService;
    private V mView;

    public ApiMethods(V view) {
        androidService = RetrofitManager.getInstance().createApi(WanAndroidService.class);
        this.mView = view;
    }

    /**
     * 封装线程管理和订阅的过程
     */
    public void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
    }


    /**
     * 获取用户登录信息
     *
     * @param observer 由调用者传过来的观察者对象
     */
    public void getWanInfo(Observer<ResultInfo<List<BeanData>>> observer, Map<String, Object> maps) {
        androidService.getPublicAccountList(maps)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
              //  .compose(RxLifecycle.bindUntilEvent(mView.getLifeCycleSubject(), ActivityEvent.DESTROY))
                .subscribe(observer);
        //  ApiSubscribe(observable, observer);
    }

}
