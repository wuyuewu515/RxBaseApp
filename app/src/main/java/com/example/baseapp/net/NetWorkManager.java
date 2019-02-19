package com.example.baseapp.net;

import com.example.baseapp.BuildConfig;
import com.example.baseapp.net.api.Example2API;
import com.example.baseapp.net.api.ExampleAPI;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: Five_伍
 * @create: 2019/1/24
 * @Describe: API 初始化类
 */
public class NetWorkManager {

    private volatile static NetWorkManager manager;
    private static Retrofit retrofit;
    private static volatile ExampleAPI request = null;
    private static volatile Example2API request2 = null;

    private NetWorkManager() {
    }

    public static NetWorkManager getInstance() {
        if (manager == null) {
            synchronized (NetWorkManager.class) {
                if (manager == null) {
                    manager = new NetWorkManager();
                }
            }
        }
        return manager;
    }

    /**
     * 初始化必要的对象和参数
     */
    public void init() {

        //请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("ver", BuildConfig.VERSION_NAME);

        CommonInterceptor commonInterceptor = new CommonInterceptor.Builder()
                .addHeaderParamsMap(headers)
                .addParam("sign", "zuojia")
                .addParam("os", "android")
                .build();

        //初始化okHttp
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(commonInterceptor) //添加公共的请求头
                .build();

        //初始化 Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加这个表明返回对象是observable
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    //获取模块api方法
    public ExampleAPI getExampleAPI() {
        if (request == null) {
            synchronized (ExampleAPI.class) {
                request = retrofit.create(ExampleAPI.class);
            }
        }
        return request;
    }

    //获取其他模块模块api方法
    public Example2API getExampleAPI2() {
        if (request2 == null) {
            synchronized (Example2API.class) {
                request2 = retrofit.create(Example2API.class);
            }
        }
        return request2;
    }


}
