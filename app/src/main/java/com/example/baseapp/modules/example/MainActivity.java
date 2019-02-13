package com.example.baseapp.modules.example;

import android.Manifest;
import android.os.Bundle;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBtn.setText("你好啊");
        tvBtn2.setText("点击我试试");

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

    @OnClick(R.id.tv_btn2)
    public void onViewClicked() {

        mPresenter.getLoginUserInfo();

    }

}