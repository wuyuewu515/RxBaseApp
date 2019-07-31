package com.example.baseapp;

import android.app.Application;
import android.content.Context;

/**
 * @author: Five_伍
 * @create: 2019/1/24
 * @Describe:
 */
public class MyApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }


}
