package com.example.baseapp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.baseapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author: Five_伍
 * @create: 2019/2/12
 * @Describe: activity的基类
 */
public abstract class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity implements BaseView, EasyPermissions.PermissionCallbacks {
    protected Activity mActivity;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentView() != null)
            setContentView(getContentView());
        else
            setContentView(getContentId());

        mActivity = this;

        bindVP();

    }

    @Override
    public Context getContext() {
        return mActivity;
    }

    /***
     * 绑定view层和presenter层
     */
    protected abstract void bindVP();

    @Override
    public void showToast(String info) {

    }

    /**
     * 返回视图
     *
     * @return
     */
    protected View getContentView() {
        return null;
    }


    /**
     * 获取空布局
     *
     * @return
     */
    protected View getEmptyView() {
        View view = LayoutInflater.from(this).inflate(R.layout.empty_layout, null, false);
        return view;
    }

    /**
     * 返回视图ID
     *
     * @return
     */
    protected Integer getContentId() {
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
