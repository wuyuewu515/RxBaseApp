package com.example.baseapp.modules.personal;

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
public class PersonalFragment extends BaseFragment<PersonalPresenter> implements PersonalContract.View {
    @BindView(R.id.titleBar)
    TitleBar titleBar;

    @Override
    protected void bindVP() {
        mPresenter = new PersonalPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected Integer getContentId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {
        titleBar.setLeftIcon(0);
    }

    @Override
    protected void initData() {

        LogUtils.LOG_D(getClass(), "个人中心初始化");
    }
}
