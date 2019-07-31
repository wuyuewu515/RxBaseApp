package com.example.baseapp.modules.login;

import com.example.baseapp.bean.BeanData;
import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.net.ApiObserver;
import com.example.baseapp.net.NetException;
import com.example.baseapp.utils.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;

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
        map.put("loginName", "five_wu");
        map.put("pwd", "123");
        map.put("sign", 456);

        Observer<ResultInfo<List<BeanData>>> observer = new ApiObserver<List<BeanData>>(this) {

            @Override
            public void onApiSuccess(List<BeanData> data) {
                //请求成功
                for (int i = 0; i < data.size(); i++) {
                    LogUtils.LOG_D(LoginPresenter.class, "用户:" + data.get(i).getName());
                }
                mView.showToast("请求成功");
            }

            @Override
            public void onApiError(NetException.ResponseException apiExcption) {
                mView.showToast(apiExcption.message);
            }
        };


        apiMethods.getWanInfo(observer, map);


    }
}
