package com.example.baseapp.modules.mycar;

import com.example.baseapp.R;
import com.example.baseapp.base.BaseFragment;
import com.example.baseapp.utils.LogUtils;
import com.example.baseapp.views.TitleBar;

import butterknife.BindView;

/**
 * @author: Five_伍
 * @create: 2019/2/19
 * @Describe:
 */
public class MyCarFragment extends BaseFragment<MyCarPresenter> implements MyCarContract.View {
    @BindView(R.id.titleBar)
    TitleBar titleBar;

    @Override
    protected void bindVP() {
        mPresenter = new MyCarPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected Integer getContentId() {
        return R.layout.fragment_mycar;
    }

    @Override
    protected void initView() {
        titleBar.setLeftIcon(0);
    }

    @Override
    protected void initData() {
        LogUtils.LOG_D(getClass(), "我的车辆初始化");

    }
}
