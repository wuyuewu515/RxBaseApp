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
     * @return
     */
    Context getContext();

    void showToast(String info);
}

