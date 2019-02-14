package com.example.baseapp.net;

import android.util.Log;

import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.exception.ApiException;
import com.example.baseapp.exception.CustomException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author: Five_伍
 * @create: 2019/1/24
 * @Describe: 接口返回的数据进行封装
 */
public abstract class ApiObserver<T> implements Observer<ResultInfo<T>> {
    public ApiObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(ResultInfo<T> responseBody) {

        Log.i("ApiObserver", "responseBody====" + responseBody);

        if (null == responseBody) { //服务器错误
            onApiError(new ApiException(-1, "返回数据为空"));
        } else { //判断返回码的状态
            int code = responseBody.getCode();

            switch (code) {
                case 1: //数据成功返回
                    onApiSuccess(responseBody.getData());
                    break;
                default:
                    onApiError(new ApiException(code, responseBody.getMessage()));
                    break;
            }
        }

    }

    @Override
    public void onError(Throwable e) {
        Log.i("ApiObserver", "e====" + e.getMessage());
        onApiError(CustomException.handleException(e));
    }

    @Override
    public void onComplete() {

    }


    public abstract void onApiSuccess(T data);

    public abstract void onApiError(ApiException apiExcption);
}
