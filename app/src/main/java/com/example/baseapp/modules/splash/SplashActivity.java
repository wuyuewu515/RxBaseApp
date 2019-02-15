package com.example.baseapp.modules.splash;

import android.os.Handler;

import com.example.baseapp.R;
import com.example.baseapp.base.BaseActivity;
import com.example.baseapp.modules.example.MainActivity;

/**
 * @author: Five_伍
 * @create: 2019/2/14
 * @Describe: 启动页面
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void bindVP() {

    }

    @Override
    protected Integer getContentId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.inTo(mActivity);
                finish();
            }
        }, 1500);
    }
}
