package me.jokey.mvp.model;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import me.jokey.mvp.application.BaseApplication;
import me.jokey.mvp.model.api.GankApi;
import me.jokey.mvp.model.api.ZhihuApi;
import me.jokey.mvp.utils.StateUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zuozuoyou on 2017/4/28.
 */

public abstract class BaseModel<T> {
    protected Retrofit retrofit;
    private static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";
    public static final String GANK_BASE_URL = "http://gank.io/api/";
    public static final String DAILY_BASE_URL = "http://app3.qdaily.com/app3/";
    private String baseUrl;
    protected T api;



    public BaseModel(Class cls) {
        if (cls.isAssignableFrom(GankApi.class)){
            baseUrl = GANK_BASE_URL;
        }else if (cls.isAssignableFrom(ZhihuApi.class)){
            baseUrl = ZHIHU_BASE_URL;
        }
        initModel();
        api = (T) retrofit.create(cls);
    }

    public void initModel() {

        File httpCacheDirectory = new File(BaseApplication.getContext().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {

            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();

            Request request = chain.request();
            if (!StateUtils.isNetworkAvailable(BaseApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (StateUtils.isNetworkAvailable(BaseApplication.getContext())) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache).build();
        retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        initModel();
    }


}
