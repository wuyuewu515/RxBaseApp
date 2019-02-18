package com.example.baseapp.modules.login;

import android.util.Log;

import com.example.baseapp.bean.UserInfo;
import com.example.baseapp.bean.UserLoginInfo;
import com.example.baseapp.exception.ApiException;
import com.example.baseapp.net.ApiObserver;
import com.example.baseapp.net.api.ApiMethods;
import com.example.baseapp.utils.LogUtils;
import com.example.baseapp.utils.TelephonyUtils;

/**
 * @author: Five_伍
 * @create: 2019/2/15
 * @Describe: 登录操作
 */
public class LoginPresenter extends LoginContract.Presenter {
    @Override
    protected void start() {
        apiMethods = new ApiMethods();
    }

    @Override
    void login() {
        if (!mView.checkInput())
            return;

        ApiObserver<UserLoginInfo> observer = new ApiObserver<UserLoginInfo>(mView) {
            @Override
            public void onApiSuccess(UserLoginInfo data) {
                if (null != data) {
                    UserInfo userInfo = data.getUserInfo();
                    UserInfo.getInstance().setUserData(userInfo);
                    LogUtils.LOG_D(LoginPresenter.class, "用户" + userInfo.get_name());
                    mView.intoActivity();
                }
            }

            @Override
            public void onApiError(ApiException apiException) {
                mView.showToast(apiException.getDisplayMessage());
            }
        };
        String userNumber = mView.getNumber();
        String pwd = mView.getPwd();
        apiMethods.getLoginInfo(observer, userNumber, pwd, TelephonyUtils.getDeviceId(mView.getContext()));
    }
}
