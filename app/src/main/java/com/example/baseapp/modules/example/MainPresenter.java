package com.example.baseapp.modules.example;

import android.util.Log;

import com.example.baseapp.bean.UserLoginInfo;
import com.example.baseapp.exception.ApiException;
import com.example.baseapp.net.ApiObserver;
import com.example.baseapp.net.NetWorkManager;
import com.example.baseapp.net.ResponseTransformer;
import com.example.baseapp.net.SchedulerProvider;
import com.example.baseapp.utils.TelephonyUtils;

/**
 * @author: Five_伍
 * @create: 2019/2/13
 * @Describe:
 */
public class MainPresenter extends MainContact.Presenter {
    private SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();

    @Override
    void getLoginUserInfo() {
        NetWorkManager.getInstance().getExampleAPI().login("15601645052", "123456",
                TelephonyUtils.getDeviceId(mView.getContext()))
                .compose(ResponseTransformer.handleResult())
                .compose(schedulerProvider.applySchedulers())
                .subscribe(new ApiObserver<UserLoginInfo>() {

                    @Override
                    public void onApiSuccess(UserLoginInfo data) {
                        Log.i("MainActivity", "getMessage===：流程成功...");
                        Log.i("MainActivity", "data===" + data.toString());
                    }

                    @Override
                    public void onApiError(ApiException apiException) {
                        Log.i("MainActivity", "getMessage===：" + apiException.getDisplayMessage());
                        Log.i("MainActivity", "getCode===：" + apiException.getCode());
                        mView.showToast(apiException.getDisplayMessage());
                    }
                });
    }
}
