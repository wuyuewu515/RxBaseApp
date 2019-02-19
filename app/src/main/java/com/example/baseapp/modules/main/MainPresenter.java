package com.example.baseapp.modules.main;

import com.example.baseapp.bean.UserInfo;
import com.example.baseapp.net.api.ApiMethods;
import com.example.baseapp.utils.LogUtils;

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
        UserInfo userInfo = UserInfo.getInstance();
        LogUtils.LOG_D(MainPresenter.class, "当前的用户是:" + userInfo.get_name());
    }


}
