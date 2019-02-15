package com.example.baseapp.base;

import android.content.Context;

/**
 * @author: Five_伍
 * @create: 2019/2/12
 * @Describe:
 */
public interface BaseView {
    /**
     * 获取上下文
     *
     * @return activity
     */
    Context getContext();

    /**
     * 展示信息
     *
     * @param info 信息
     */
    void showToast(String info);
}

