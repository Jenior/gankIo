package me.jokey.mvp.model.api;

import io.reactivex.Observable;
import me.jokey.mvp.model.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.model.entity.zhihu.ZhihuDay;
import me.jokey.mvp.model.entity.zhihu.ZhihuDetail;
import me.jokey.mvp.model.entity.zhihu.ZhihuTheme;
import me.jokey.mvp.model.entity.zhihu.ZhihuThemeDetail;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * get Zhihu with retrofit
 */
public interface ZhihuApi {

//    @GET("start-image/1080*1920")
//    Observable<SplashImage> getSplashImage();

    @GET("news/latest")
    Observable<Response<ResponseBody>> getZhihuLatest();

    @GET("news/before/{date}")
    Observable<Response<ResponseBody>>  getZhihuBeforet(@Path("date") String date);

    @GET("news/{id}")
    Observable<Response<ResponseBody>>  getZhihuDetail(@Path("id") String id);

    @GET("themes")
    Observable<Response<ResponseBody>> getZhihuThemes();

    @GET("theme/{id}")
    Observable<Response<ResponseBody>>  getZhihuThemeDetail(@Path("id") String id);
}
