package me.jokey.mvp.model.api;

import me.jokey.mvp.model.entity.gank.GankBean;
import me.jokey.mvp.model.entity.gank.ResultBean;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by wz on 2017/6/22 14:42.
 * GankApi
 */


public interface GankApi {

    @GET("data/{type}/{count}/{page}")
    Observable<GankBean<ResultBean>> getGank(@Path("type") String type, @Path("count") int count, @Path("page") int page);

    @GET
    Observable<ResponseBody> getGankImage(@Url String url);

}
