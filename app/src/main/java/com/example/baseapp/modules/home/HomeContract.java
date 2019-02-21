package com.example.baseapp.modules.home;

import com.example.baseapp.base.BasePresenter;
import com.example.baseapp.base.BaseView;

import java.util.List;

/**
 * @author: Five_伍
 * @create: 2019/2/19
 * @Describe: 主页
 */
interface HomeContract {
    interface View extends BaseView {
        void setAdapterData(List<String> datas);

        /**
         * 获取渠道号
         * @return
         */
        String getChannel();
    }

    abstract class Presenter extends BasePresenter<View> {
        /**
         *初始化数据
         */
        public abstract void initData();
    }
}
