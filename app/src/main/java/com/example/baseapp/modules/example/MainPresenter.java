package com.example.baseapp.modules.example;

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


        ApiObserver<UserLoginInfo> observer = new ApiObserver<UserLoginInfo>(mView) {

            @Override
            public void onApiSuccess(UserLoginInfo data) {
                if (null != data) {
                    UserLoginInfo.UserInfo userInfo = data.getUserInfo();
                }

            }

            @Override
            public void onApiError(ApiException apiException) {
                mView.showToast(apiException.getDisplayMessage());
            }
        };

        apiMethods.getLoginInfo(observer, "15601645052", "123456", TelephonyUtils.getDeviceId(mView.getContext()));

    }


}
