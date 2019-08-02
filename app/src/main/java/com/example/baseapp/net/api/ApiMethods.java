package com.example.baseapp.net.api;

import com.example.baseapp.base.BaseView;
import com.example.baseapp.bean.BeanData;
import com.example.baseapp.bean.ChaipiaoInfo;
import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.bean.WanNianInfo;
import com.example.baseapp.net.RetrofitManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;
import java.util.Map;

/**
 * @author: Five_伍
 * @create: 2019/2/13
 * @Describe: 所有接口调用方法，相当于model层
 */
public class ApiMethods<V extends BaseView> {
    private WanAndroidService androidService;
    private CaiPiaoService caiPiaoService;

    public ApiMethods() {
        androidService = RetrofitManager.getInstance().createApi(WanAndroidService.class);
        caiPiaoService = RetrofitManager.getInstance().createApi(CaiPiaoService.class);
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
                .subscribe(observer);
    }

    /**
     * 获取最新一期的彩票
     *
     * @param observer
     * @param maps
     */
    public void getLastNum(Observer<ResultInfo<ChaipiaoInfo>> observer, Map<String, Object> maps) {
        caiPiaoService.getLastNum(maps)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取最新一期的彩票
     *
     * @param observer
     */
    public void getWanNian(Observer<ResultInfo<WanNianInfo>> observer, String date) {
        caiPiaoService.getWanNian(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
