package com.example.baseapp.modules.example;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.baseapp.R;
import com.example.baseapp.base.BaseActivity;
import com.example.baseapp.utils.AppManager;

import java.util.Timer;
import java.util.TimerTask;

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
        if (EasyPermissions.hasPermissions(mActivity, perms)) {
            // 如果有权限了, 就做你该做的事情
            return true;
        } else {
            // 如果没有权限, 就去申请权限
            // this: 上下文
            // Dialog显示的正文
            // RC_CAMERA_AND_RECORD_AUDIO 请求码, 用于回调的时候判断是哪次申请
            // perms 就是你要申请的权限
            EasyPermissions.requestPermissions(mActivity, "要这个权限啊", READ_PHONE_STATE, perms);
            return false;
        }
    }


    @OnClick({R.id.tv_btn2, R.id.tv_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn2:
                mPresenter.getLoginUserInfo();

                break;
            case R.id.tv_btn:
                mPresenter.cancleRequest(this);

                break;
        }

    }

    public static void inTo(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    private boolean isExit = false;

    @Override
    public void onBackPressed() {

        Timer tExit = null;
        if (!isExit) {
            isExit = true; // 准备退出
            showToast("再按一次退出应用");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            //退出app
            AppManager.AppExit(mActivity);
        }
    }
}
