package com.example.baseapp.net;


import android.util.Log;

import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.exception.ApiException;
import com.example.baseapp.exception.CustomException;

import org.json.JSONException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * @author: Five_伍
 * @create: 2019/1/24
 * @Describe: 网络请求返回的转换类
 */
public class ResponseTransformer {

    public static ObservableTransformer handleResult() {

        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction())
                .flatMap(new ResponseFunction());

    }

    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param
     */
    private static class ErrorResumeFunction implements Function<Throwable, ObservableSource<? extends ResultInfo>> {

        @Override
        public ObservableSource<? extends ResultInfo> apply(Throwable throwable) {

            return Observable.error(CustomException.handleException(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     */
    private static class ResponseFunction implements Function<ResultInfo, ObservableSource> {

        @Override
        public ObservableSource apply(ResultInfo response) throws JSONException {

            Log.i("ResponseTransformer", response.toString());
            int code = response.getCode();
            if (code == 1) {//接口访问成功
                return Observable.just(response.getData());
            } else {
                String message = response.getMessage();
                return Observable.error(new ApiException(code, message));
            }
        }
    }
}
