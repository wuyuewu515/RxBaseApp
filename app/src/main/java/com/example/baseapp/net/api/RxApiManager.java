package com.example.baseapp.net.api;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;

import com.example.baseapp.utils.LogUtils;

import java.util.Set;

import io.reactivex.disposables.Disposable;

/**
 * @author: Five_伍
 * @create: 2019/2/14
 * @Describe: 请求管理
 */
public class RxApiManager {
    private static RxApiManager sInstance = null;

    private ArrayMap<Object, Disposable> maps;

    public static RxApiManager getsInstance() {

        if (sInstance == null) {
            synchronized (RxApiManager.class) {
                if (sInstance == null) {
                    sInstance = new RxApiManager();
                }
            }
        }
        return sInstance;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private RxApiManager() {
        maps = new ArrayMap<>();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void add(Object tag, Disposable disposable) {
        maps.put(tag, disposable);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void remove(Object tag) {
        if (!maps.isEmpty()) {
            maps.remove(tag);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void removeAll() {
        if (!maps.isEmpty()) {
            maps.clear();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void cancel(Object tag) {
        if (maps.isEmpty()) {
            return;
        }
        if (maps.get(tag) == null) {
            return;
        }
        if (!maps.get(tag).isDisposed()) {
            LogUtils.LOG_D(RxApiManager.class, "需要取消的请求的是：" + maps.get(tag));
            maps.get(tag).dispose();
            maps.remove(tag);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void cancelAll() {
        if (maps.isEmpty()) {
            return;
        }
        Set<Object> keys = maps.keySet();
        for (Object apiKey : keys) {
            cancel(apiKey);
        }
    }

}
