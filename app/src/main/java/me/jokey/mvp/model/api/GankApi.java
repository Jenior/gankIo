package me.jokey.mvp.model.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;


/**
 * Created by wz on 2017/6/22 14:42.
 * GankApi
 */


public interface GankApi {

    @GET("data/{type}/{count}/{page}")
    Observable<Response<ResponseBody>> getGank(@Path("type") String type, @Path("count") int count, @Path("page") int page);

    @GET
    Observable<Response<ResponseBody>> getGankImage(@Url String url);

}
