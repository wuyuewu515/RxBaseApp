package com.example.baseapp.modules.splash;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import com.example.baseapp.BuildConfig;
import com.example.baseapp.R;
import com.example.baseapp.base.BaseActivity;
import com.example.baseapp.modules.login.LoginActiviy;
import com.example.baseapp.modules.main.MainActivity;
import com.example.baseapp.utils.ToastUtil;

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
        //获取当前app的市场渠道号
        //当且仅当进行360加固以后才能有值，在配置友盟统计的时候也不需要在清单配置文件中做处理
        //使用360加固需要导入config目录下的“360加固多渠道模板.txt”
        String channel = "unknown";
        try {
            ApplicationInfo appInfo = mActivity.getPackageManager().getApplicationInfo(mActivity.getPackageName(), PackageManager.GET_META_DATA);
            Bundle metaData = appInfo.metaData;
            if (null != metaData)
                channel = metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (BuildConfig.DEBUG) {
            showToast("当前市场是" + channel);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginActiviy.inTo(mActivity);
            }
        }, 1500);
    }
}
