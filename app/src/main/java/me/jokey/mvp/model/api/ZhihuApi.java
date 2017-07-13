package me.jokey.mvp.model.api;

import me.jokey.mvp.model.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.model.entity.zhihu.ZhihuDay;
import me.jokey.mvp.model.entity.zhihu.ZhihuDetail;
import me.jokey.mvp.model.entity.zhihu.ZhihuTheme;
import me.jokey.mvp.model.entity.zhihu.ZhihuThemeDetail;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

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
    Observable<ZhihuDay> getZhihuLatest();

    @GET("news/before/{date}")
    Observable<ZhihuBefore> getZhihuBeforet(@Path("date") String date);

    @GET("news/{id}")
    Observable<ZhihuDetail> getZhihuDetail(@Path("id") String id);

    @GET("themes")
    Observable<ZhihuTheme> getZhihuThemes();

    @GET("theme/{id}")
    Observable<ZhihuThemeDetail> getZhihuThemeDetail(@Path("id") String id);
}
