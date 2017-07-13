package me.jokey.mvp.model;

import me.jokey.mvp.model.api.ApiFactory;
import me.jokey.mvp.model.api.GankApi;
import me.jokey.mvp.contract.DownImageContract;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by wz on 2017/6/22 17:58.
 * DownImageModel
 */


public class DownImageModel implements DownImageContract.Model {

    private GankApi gankApi = ApiFactory.getGankApiSingleton();

    @Override
    public Observable<ResponseBody> getGankImage(String url) {
        return gankApi.getGankImage(url);
    }
}
