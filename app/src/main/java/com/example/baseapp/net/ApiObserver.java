package com.example.baseapp.net;

import android.content.DialogInterface;

import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.exception.ApiException;
import com.example.baseapp.exception.CustomException;
import com.example.baseapp.net.api.RxApiManager;
import com.example.baseapp.utils.AppManager;
import com.example.baseapp.utils.LoadingDialogUtils;
import com.example.baseapp.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author: Five_伍
 * @create: 2019/1/24
 * @Describe: 接口返回的数据进行封装
 */
public abstract class ApiObserver<T> implements Observer<ResultInfo<T>> {
    private RxApiManager rxApiManager = RxApiManager.getsInstance();
    private Object tag;
    //需要显示加载框
    private boolean needShowLoading;
    private LoadingDialogUtils dialogUtils;
    //loading弹窗消失监听
    private DialogInterface.OnDismissListener listener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            if (null != rxApiManager)
                rxApiManager.cancel(tag);
        }
    };

    public ApiObserver(Object tag) {
        this.tag = tag;
        this.needShowLoading = true;
        dialogUtils = LoadingDialogUtils.getInstance();
    }

    /**
     * @param tag             标志位
     * @param needShowLoading 是否需要loading效果
     */
    public ApiObserver(Object tag, boolean needShowLoading) {
        this.needShowLoading = needShowLoading;
        this.tag = tag;
        dialogUtils = LoadingDialogUtils.getInstance();
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (needShowLoading) {
            dialogUtils.show(AppManager.currentActivity());
        }
        dialogUtils.setDismissListener(listener);
        rxApiManager.add(tag, d);
    }


    @Override
    public void onNext(ResultInfo<T> responseBody) {
        dialogUtils.dismiss(AppManager.currentActivity());
        LogUtils.LOG_D(ApiObserver.class, "responseBody====" + responseBody);
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
        dialogUtils.dismiss(AppManager.currentActivity());
        LogUtils.LOG_D(ApiObserver.class, "e====" + e.getMessage());
        onApiError(CustomException.handleException(e));
    }

    @Override
    public void onComplete() {

    }

    public abstract void onApiSuccess(T data);

    public abstract void onApiError(ApiException apiExcption);
}
