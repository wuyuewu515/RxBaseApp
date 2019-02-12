package com.example.baseapp.net;

import android.util.Log;

import com.example.baseapp.exception.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author: Five_伍
 * @create: 2019/1/24
 * @Describe:
 */
public abstract class ApiObserver<T> implements Observer<T> {
    public ApiObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T responseBody) {
        Log.i("ApiObserver", "responseBody====" + responseBody);

        if (null == responseBody) {
            onApiError(new ApiException(-1, "返回数据为空"));
        } else {
            onApiSuccess(responseBody);
        }

    }

    @Override
    public void onError(Throwable e) {
        Log.i("ApiObserver", "e====" + e.getMessage());
        onApiError((ApiException) e);
    }

    @Override
    public void onComplete() {

    }


    public abstract void onApiSuccess(T data);

    public abstract void onApiError(ApiException apiExcption);
}
