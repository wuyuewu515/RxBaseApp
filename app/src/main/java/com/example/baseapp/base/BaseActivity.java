package com.example.baseapp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.baseapp.R;
import com.example.baseapp.utils.AppManager;
import com.example.baseapp.utils.LogUtils;
import com.example.baseapp.utils.StatusBarUtil;
import com.example.baseapp.utils.ToastUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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
    protected Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentView() != null)
            setContentView(getContentView());
        else
            setContentView(getContentId());

        bind = ButterKnife.bind(this);
        mActivity = this;
        AppManager.addActivity(this);

        initView();
        initListener();
        initData();
        bindVP();
        //状态栏处理
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }

        StatusBarUtil.transparencyBar(this);


    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }


    @Override
    public Context getContext() {
        return mActivity;
    }

    /***
     * 绑定view层和presenter层
     * 例如  mPresenter = new MainPresenter();
     *         mPresenter.attachView(this);
     */
    protected abstract void bindVP();

    @Override
    public void showToast(String info) {
        ToastUtil.showToast(info);
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
        //释放资源
        if (null != bind)
            bind.unbind();
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter.cancleRequest(this);
            mPresenter = null;
        }
        LogUtils.LOG_D(BaseActivity.class, "ondestory");
        AppManager.removeActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.LOG_D(BaseActivity.class, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.LOG_D(BaseActivity.class, "onStop");

    }
}
