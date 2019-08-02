package com.example.baseapp.net.api;


import com.example.baseapp.bean.ChaipiaoInfo;
import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.bean.WanNianInfo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * 彩票接口地址
 */
public interface CaiPiaoService {

    /**
     * 获取指定日期的彩票
     *
     * @Call<ResponseBody>
     */
    @GET("lottery/ssq/aim_lottery")
    Observable<ResultInfo<ChaipiaoInfo>> getLastNum(@QueryMap Map<String, Object> mpas);

    /**
     * 获取指定日期的万年历信息
     *
     * @Call<ResponseBody>
     */
    @GET("holiday/single/{date}")
    Observable<ResultInfo<WanNianInfo>> getWanNian(@Path("date") String date);
}
