package com.example.baseapp.net.api;


import com.example.baseapp.bean.BeanData;
import com.example.baseapp.bean.ResultInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @ClassName: WanAndroidService
 * @Author: 伍跃武
 * @Date: 2019/7/30 15:57
 * @Description:
 */
public interface WanAndroidService {
    /**
     * 获取公众号列表
     * @Call<ResponseBody>
     */
    @GET("wxarticle/chapters/json")
    //baseUrl+接口中配置的地址组成真正的请求地址。
  //  @FormUrlEncoded
    Observable<ResultInfo<List<BeanData>>> getPublicAccountList(@QueryMap Map<String,Object> map);

}
