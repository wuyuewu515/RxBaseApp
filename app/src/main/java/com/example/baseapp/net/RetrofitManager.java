package com.example.baseapp.net;

import com.example.baseapp.BuildConfig;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName: RetrofitManager
 * @Author: 伍跃武
 * @Date: 2019/7/30 16:47
 * @Description: 网络请求初始化
 */
public class RetrofitManager {
    private Retrofit mRetrofit;

    private static class InstanceHelper {
        static RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return InstanceHelper.instance;
    }

    private RetrofitManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(createOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient createOkhttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        Proxy proxy = BuildConfig.DEBUG ? null : Proxy.NO_PROXY;
        return new OkHttpClient.Builder()
                //设置连接超时时间
                .connectTimeout(30, TimeUnit.SECONDS)
                //设置读取超时时间
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                //添加日志过滤器
                .addInterceptor(httpLoggingInterceptor)
                .proxy(proxy) //设置代理
                //添加BaseInterceptor拦截器
                .addInterceptor(new BaseInterceptor())
                .build();
    }

    public <T> T createApi(final Class<T> service) {
        return mRetrofit.create(service);
    }
}
