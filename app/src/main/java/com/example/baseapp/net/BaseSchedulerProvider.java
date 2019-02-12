package com.example.baseapp.net;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

/**
 * @author: Five_伍
 * @create: 2019/1/24
 * @Describe:
 */
public interface BaseSchedulerProvider {
    Scheduler computation();

    Scheduler io();

    Scheduler ui();

    <T> ObservableTransformer<T, T> applySchedulers();
}
