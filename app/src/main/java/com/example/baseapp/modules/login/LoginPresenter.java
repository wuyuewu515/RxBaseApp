package com.example.baseapp.modules.login;

import com.example.baseapp.bean.ChaipiaoInfo;
import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.bean.WanNianInfo;
import com.example.baseapp.net.ApiObserver;
import com.example.baseapp.net.NetException;
import com.example.baseapp.net.RetrofitManager;
import com.example.baseapp.net.api.CaiPiaoService;
import com.example.baseapp.net.api.RxApiManager;
import com.example.baseapp.net.api.WanAndroidService;
import com.example.baseapp.utils.LogUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Five_伍
 * @create: 2019/2/15
 * @Describe: 登录操作
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    protected void start() {

    }

    @Override
    void login() {
        if (!mView.checkInput())
            return;

        Map<String, Object> map = new HashMap<>();
        map.put("expect", "2019088");
        map.put("date", "20190801");
        map.put("sign", 456);

        CaiPiaoService api = RetrofitManager.getInstance().createApi(CaiPiaoService.class);


        Observable.merge(api.getLastNum(map), api.getWanNian("20190801"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultInfo<? extends Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        RxApiManager.getsInstance().add(mView, d);
                    }

                    @Override
                    public void onNext(ResultInfo<?> resultInfo) {
                        LogUtils.LOG_D(LoginPresenter.class, "请求成功：" + resultInfo.getCode());

                        if (resultInfo.getData() instanceof WanNianInfo) {
                            WanNianInfo data = (WanNianInfo) resultInfo.getData();
                            LogUtils.LOG_D(LoginPresenter.class, "一年的第：" + data.getDayOfYear());
                            LogUtils.LOG_D(LoginPresenter.class, "避免做：" + data.getAvoid());

                        } else if (resultInfo.getData() instanceof ChaipiaoInfo) {
                            ChaipiaoInfo data = (ChaipiaoInfo) resultInfo.getData();
                            LogUtils.LOG_D(LoginPresenter.class, "开奖号码：" + data.getOpenCode());
                            LogUtils.LOG_D(LoginPresenter.class, "开奖期数：" + data.getExpect());
                            LogUtils.LOG_D(LoginPresenter.class, "开奖日期：" + data.getTime());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.LOG_D(LoginPresenter.class, "请求失败：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.LOG_D(LoginPresenter.class, "请求结束");
                        RxApiManager.getsInstance().cancel(mView);
                    }
                });
        //  apiMethods.getLastNum(observer, map);

        //   apiMethods.getWanNian(dateObserver, "20190801");
    }


    private Observer<ResultInfo<WanNianInfo>> dateObserver = new ApiObserver<WanNianInfo>(mView) {
        @Override
        public void onApiSuccess(WanNianInfo data) {
            LogUtils.LOG_D(LoginPresenter.class, "：" + data.getDayOfYear());
            LogUtils.LOG_D(LoginPresenter.class, "：" + data.getAvoid());
        }

        @Override
        public void onApiError(NetException.ResponseException exception) {

        }
    };


    private Observer<ResultInfo<ChaipiaoInfo>> observer = new ApiObserver<ChaipiaoInfo>(mView) {
        @Override
        public void onApiSuccess(ChaipiaoInfo data) {
            LogUtils.LOG_D(LoginPresenter.class, "开奖号码：" + data.getOpenCode());
            LogUtils.LOG_D(LoginPresenter.class, "开奖期数：" + data.getExpect());
            LogUtils.LOG_D(LoginPresenter.class, "开奖日期：" + data.getTime());
        }

        @Override
        public void onApiError(NetException.ResponseException exception) {
            mView.showToast(exception.message);
        }
    };


}
