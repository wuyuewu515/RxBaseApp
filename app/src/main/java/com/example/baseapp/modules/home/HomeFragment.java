package com.example.baseapp.modules.home;

import com.example.baseapp.R;
import com.example.baseapp.base.BaseFragment;
import com.example.baseapp.utils.LogUtils;

/**
 * @author: Five_伍
 * @create: 2019/2/19
 * @Describe:
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
    @Override
    protected void bindVP() {
        mPresenter = new HomePresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected Integer getContentId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        LogUtils.LOG_D(getClass(), "首页初始化");

    }
}
