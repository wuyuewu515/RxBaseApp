package com.example.baseapp.modules.example;

import android.Manifest;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.baseapp.R;
import com.example.baseapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContact.View {

    public static final int READ_PHONE_STATE = 10001;
    @BindView(R.id.tv_btn)
    TextView tvBtn;
    @BindView(R.id.tv_btn2)
    TextView tvBtn2;


    @Override
    protected Integer getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tvBtn.setText("啦啦啦啦");
        tvBtn2.setText("这个是按钮");
    }

    @Override
    protected void initData() {
        requestPermission();
    }

    @Override
    protected void bindVP() {
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
    }

    private boolean requestPermission() {
        String[] perms = {Manifest.permission.READ_PHONE_STATE};

        //判断有没有权限
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 如果有权限了, 就做你该做的事情
            return true;
        } else {
            // 如果没有权限, 就去申请权限
            // this: 上下文
            // Dialog显示的正文
            // RC_CAMERA_AND_RECORD_AUDIO 请求码, 用于回调的时候判断是哪次申请
            // perms 就是你要申请的权限
            EasyPermissions.requestPermissions(this, "要这个权限啊", READ_PHONE_STATE, perms);
            return false;
        }
    }


    @OnClick({R.id.tv_btn2, R.id.tv_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn2:
                Log.i("MainActivity", "getMessage===：流程开始...");
                mPresenter.getLoginUserInfo();

                break;
            case R.id.tv_btn:
                Log.i("MainActivity", "取消的请求的是：" + this);
                mPresenter.cancleRequest(this);

                break;
        }

    }

}
