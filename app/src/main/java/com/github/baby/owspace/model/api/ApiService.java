package com.github.baby.owspace.model.api;

import com.github.baby.owspace.model.entity.Detail;
import com.github.baby.owspace.model.entity.Result;
import com.github.baby.owspace.model.entity.SplashEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * Owspace
 */
public interface ApiService {
    /**
     * <p>http://static.owspace.com/static/picture_list.txt?client=android&version=1.3.0&time=1467864021&device_id=866963027059338</p>
     * @param client
     * @param version
     * @param time
     * @param imei
     * @return
     */
    @GET("static/picture_list.txt")
    Call<SplashEntity> getSplash(@Query("client") String client,@Query("version") String version,@Query("time") Long time,@Query("device_id") String imei);
    /**
     * http://static.owspace.com/?c=api&a=getPost&post_id=292296&show_sdv=1
     * <p>详情页</p>
     * @param c
     * @param a
     * @param post_id
     * @param show_sdv
     * @return
     */
    @GET("/")
    Call<Result.Data<Detail>> getDetail(@Query("c")String c, @Query("a") String a, @Query("post_id") int post_id, @Query("show_sdv") int show_sdv);
}
