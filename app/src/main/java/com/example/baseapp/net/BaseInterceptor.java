package com.example.baseapp.net;

import com.example.baseapp.utils.LogUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName: BaseInterceptor
 * @Author: 伍跃武
 * @Date: 2019/7/30 16:50
 * @Description:
 */
public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
         //POST请求
        if (request.method().equals("POST")) {
            FormBody formBody = null;
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            if (request.body() instanceof FormBody) {
                formBody = (FormBody) request.body();
                //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.add(formBody.name(i), formBody.value(i));
                }
                //添加公共参数
                formBody = bodyBuilder
                        .add("os", "android")
                        .add("version", "1.0")
                        .build();

                long timeStamp = System.currentTimeMillis();

                //添加请求头
                request = request
                        .newBuilder()
                        .post(formBody)
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .addHeader("User-Agent", "android")
                        .addHeader("timeStamp", timeStamp+"") //时间戳
                        .build();
            }
            String s = request.toString();
            LogUtils.LOG_D(BaseInterceptor.class,"请求的地址是"+s);

            return chain.proceed(request);
        } else {
            //添加公共参数
            HttpUrl.Builder urlBuilder = request.url()
                    .newBuilder()
                    .addQueryParameter("pubParam1", "1")
                    .addQueryParameter("pubParam2", "2")
                    .addQueryParameter("pubParam3", "3");

            long timeStamp = System.currentTimeMillis();

            //添加请求头
            Request.Builder newBuilder = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(urlBuilder.build())
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .addHeader("timeStamp", timeStamp+"")
                    .addHeader("User-Agent", "android");

            String s = request.toString();
            LogUtils.LOG_D(BaseInterceptor.class,"请求的地址是"+s);
            return chain.proceed(newBuilder.build());
        }

    }

}
