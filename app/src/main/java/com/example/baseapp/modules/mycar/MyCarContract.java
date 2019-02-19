package com.example.baseapp.modules.mycar;

import com.example.baseapp.base.BasePresenter;
import com.example.baseapp.base.BaseView;

/**
 * @author: Five_伍
 * @create: 2019/2/19
 * @Describe: 主页
 */
interface MyCarContract {
    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter<View> {
    }
}
