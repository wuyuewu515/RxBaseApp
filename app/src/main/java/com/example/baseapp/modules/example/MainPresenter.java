package com.example.baseapp.modules.example;

import android.util.Log;

import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.bean.UserLoginInfo;
import com.example.baseapp.exception.ApiException;
import com.example.baseapp.net.ApiObserver;
import com.example.baseapp.net.api.ApiMethods;
import com.example.baseapp.utils.TelephonyUtils;

/**
 * @author: Five_伍
 * @create: 2019/2/13
 * @Describe:
 */
public class MainPresenter extends MainContact.Presenter {
    @Override
    protected void start() {
        apiMethods = new ApiMethods();
    }

    @Override
    void getLoginUserInfo() {


        ApiObserver<UserLoginInfo> observer = new ApiObserver<UserLoginInfo>() {

            @Override
            public void onApiSuccess(UserLoginInfo data) {
                Log.i("MainActivity", "getMessage===：流程成功...");
                if (null != data) {
                    UserLoginInfo.UserInfo userInfo = data.getUserInfo();
                    Log.i("MainActivity", "userLoginInfo===" + userInfo.toString());
                }

            }

            @Override
            public void onApiError(ApiException apiException) {
                Log.i("MainActivity", "getMessage===：" + apiException.getDisplayMessage());
                Log.i("MainActivity", "getCode===：" + apiException.getCode());
                mView.showToast(apiException.getDisplayMessage());
            }
        };

        apiMethods.getLoginInfo(observer, "15601645052", "1234567", TelephonyUtils.getDeviceId(mView.getContext()));

    }


}
