package com.example.baseapp.net.api;

import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.bean.UserLoginInfo;
import com.example.baseapp.net.ApiObserver;
import com.example.baseapp.net.NetWorkManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: Five_伍
 * @create: 2019/2/13
 * @Describe: 所有接口调用方法，相当于model层
 */
public class ApiMethods {
    //网络访问类
    private NetWorkManager netWorkManager = NetWorkManager.getInstance();

    /**
     * 封装线程管理和订阅的过程
     */
    public static void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 获取用户登录信息
     *
     * @param observer 由调用者传过来的观察者对象
     * @param userName 用户名
     * @param pwd      密码
     * @param deviceId 设备号
     */
    public void getLoginInfo(Observer<ResultInfo<UserLoginInfo>> observer,
                             String userName, String pwd, String deviceId) {
        Observable observable = netWorkManager.getExampleAPI().login(userName, pwd,
                deviceId);
        ApiSubscribe(observable, observer);
    }

}
