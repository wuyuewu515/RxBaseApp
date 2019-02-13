package com.example.baseapp.modules.example;

import com.example.baseapp.base.BasePresenter;
import com.example.baseapp.base.BaseView;

/**
 * @author: Five_伍
 * @create: 2019/2/13
 * @Describe:
 */
public interface MainContact {

    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter<View> {
        /**
         * 获取用户的登录信息
         */
        abstract void getLoginUserInfo();
    }
}
