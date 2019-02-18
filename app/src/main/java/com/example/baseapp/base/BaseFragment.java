package com.example.baseapp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseapp.utils.ToastUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author: Five_伍
 * @create: 2019/2/15
 * @Describe: fragment的基类
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView, EasyPermissions.PermissionCallbacks {
    protected Activity mActivity;
    protected P mPresenter;
    protected boolean isCreate = false;
    protected Unbinder bind;
    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = getActivity();
        bindVP();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getContentId() == null ? getContentView() : inflater.inflate(getContentId(), container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        isCreate = false;
        bind = ButterKnife.bind(this, view);
        initView();
        initData();
        initListener();
    }


    @Override
    public void showToast(String info) {
        ToastUtil.showToast(info);
    }


    @Override
    public Context getContext() {
        return mActivity;
    }

    protected Integer getContentId() {
        return null;
    }

    protected View getContentView() {
        return null;
    }

    /***
     * 绑定view层和presenter层
     */
    protected abstract void bindVP();

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != bind)
            bind.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.cancleRequest(this);
            mPresenter = null;
        }
    }
}
