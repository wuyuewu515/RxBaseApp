package com.example.baseapp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseapp.utils.LogUtils;
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
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements
        BaseView, EasyPermissions.PermissionCallbacks, View.OnTouchListener {
    /**
     * 贴附的activity
     */
    protected Activity mActivity;
    protected P mPresenter;
    protected Unbinder bind;

    /**
     * 是否对用户可见
     */
    private boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    private boolean mIsPrepare;
    /**
     * 根view
     */
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.LOG_D(BaseFragment.class, "onCreate---");

        super.onCreate(savedInstanceState);
        bindVP();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();

        LogUtils.LOG_D(BaseFragment.class, "onAttach---");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.LOG_D(BaseFragment.class, "onCreateView---");

        return getContentId() == null ? getContentView() : inflater.inflate(getContentId(), container, false);
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        //  super.setUserVisibleHint(isVisibleToUser);
//        LogUtils.LOG_D(BaseFragment.class, "setUserVisibleHint---" + isVisibleToUser);
//
//        this.mIsVisible = isVisibleToUser;
//        if (isVisibleToUser) {
//            onVisibleToUser();
//        }
//    }

    //单个activity中使用这个，setUserVisibleHint在viewpageradapter才有用
    //   @Override
//    public void onHiddenChanged(boolean hidden) {
//        LogUtils.LOG_D(BaseFragment.class, "onHiddenChanged---" + hidden);
//
//        this.mIsVisible = !hidden;
//        if (!hidden) {
//            onVisibleToUser();
//        }
//    }

    /**
     * 用户可见时执行的操作
     */
//    protected void onVisibleToUser() {
//        if (mIsPrepare && mIsVisible) {
//            //懒加载，当页面可见时才进行操作
//            initData();
//        }
//    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtils.LOG_D(BaseFragment.class, "onViewCreated---");
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        mIsPrepare = true;
        bind = ButterKnife.bind(this, view);
        initView();
        initData();
        initListener();
        // 拦截触摸事件，防止泄露下去
        view.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
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
     * 例如  mPresenter = new MainPresenter();
     *         mPresenter.attachView(this);
     */
    protected abstract void bindVP();

    protected void initView() {
    }

    protected void initData() {
        LogUtils.LOG_D(BaseFragment.class, "initData方法执行");


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
