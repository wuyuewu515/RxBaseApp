package com.example.baseapp;

import android.app.Application;

import com.example.baseapp.net.NetWorkManager;

/**
 * @author: Five_伍
 * @create: 2019/1/24
 * @Describe:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManager.getInstance().init();
    }
}
