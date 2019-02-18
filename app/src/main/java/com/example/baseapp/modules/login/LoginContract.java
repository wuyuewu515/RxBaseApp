package com.example.baseapp.modules.login;

import com.example.baseapp.base.BasePresenter;
import com.example.baseapp.base.BaseView;

/**
 * @author: Five_伍
 * @create: 2019/2/15
 * @Describe:
 */
public interface LoginContract {
    interface View extends BaseView {
        String getNumber();

        String getPwd();

        boolean checkInput();

        void  intoActivity();
    }

    abstract class Presenter extends BasePresenter<View> {

        abstract void login();
    }
}
