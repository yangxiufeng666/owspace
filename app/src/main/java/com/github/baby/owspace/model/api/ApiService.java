package com.github.baby.owspace.model.api;

import com.github.baby.owspace.model.entity.DetailEntity;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.model.entity.Result;
import com.github.baby.owspace.model.entity.SplashEntity;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * Owspace
 */
public interface ApiService {
    /**
     * <p>http://static.owspace.com/static/picture_list.txt?client=android&version=1.3.0&time=1467864021&device_id=866963027059338</p>
     *
     * @param client
     * @param version
     * @param time
     * @param deviceId
     * @return
     */
    @GET("static/picture_list.txt")
    Observable<SplashEntity> getSplash(@Query("client") String client, @Query("version") String version, @Query("time") Long time, @Query("device_id") String deviceId);

    /**
     * http://static.owspace.com/?c=api&a=getPost&post_id=292296&show_sdv=1
     * <p>详情页</p>
     *
     * @param c
     * @param a
     * @param post_id
     * @param show_sdv
     * @return
     */
    @GET("/")
    Observable<Result.Data<DetailEntity>> getDetail(@Query("c") String c, @Query("a") String a, @Query("post_id") String post_id, @Query("show_sdv") int show_sdv);

    /**
     * <p>分类列表</p>
     * <p>http://static.owspace.com/?c=api&a=getList&p=1&model=1&page_id=0&create_time=0&client=android&version=1.3.0&time=1467867330&device_id=866963027059338&show_sdv=1</p>
     *
     * @param c
     * @param a
     * @param page
     * @param model(0:首页，1：文字，2：声音，3：影像，4：单向历)
     * @param pageId
     * @param time
     * @param deviceId
     * @param show_sdv
     * @return
     */
    @GET("/")
    Observable<Result.Data<List<Item>>> getList(@Query("c") String c, @Query("a") String a, @Query("p") int page, @Query("model") int model, @Query("page_id") String pageId, @Query("create_time") String createTime, @Query("client") String client, @Query("version") String version, @Query("time") long time, @Query("device_id") String deviceId, @Query("show_sdv") int show_sdv);

    /**
     * http://static.owspace.com/index.php?m=Home&c=Api&a=getLunar&client=android&device_id=866963027059338&version=866963027059338
     * @return
     */
    @GET("index.php")
    Observable<String> getRecommend(@Query("m") String m,@Query("c") String api,@Query("a") String a,@Query("client") String client,@Query("version") String version, @Query("device_id") String deviceId);
}
