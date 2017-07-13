package me.jokey.mvp.api;

/**
 * Created by wz on 2017/6/20 15:26.
 * ApiFactory.java
 */

public class ApiFactory {

    private static final Object monitor = new Object();
    static ZhihuApi zhihuApiSingleton = null;
    static GankApi gankApiSingleton = null;

    //return Singleton
    public static ZhihuApi getZhihuApiSingleton() {
        synchronized (monitor) {
            if (zhihuApiSingleton == null) {
                zhihuApiSingleton = new ApiRetrofit().getZhihuApiService();
            }
            return zhihuApiSingleton;
        }
    }

    public static GankApi getGankApiSingleton() {
        synchronized (monitor) {
            if (gankApiSingleton == null) {
                gankApiSingleton = new ApiRetrofit().getGankApiService();
            }
            return gankApiSingleton;
        }
    }

//    public static DailyApi getDailyApiSingleton() {
//        synchronized (monitor) {
//            if (dailyApiSingleton == null) {
//                dailyApiSingleton = new ApiRetrofit().getDailyApiService();
//            }
//            return dailyApiSingleton;
//        }
//    }
}
