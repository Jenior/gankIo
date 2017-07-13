package me.jokey.mvp.mvp.model;

import me.jokey.mvp.api.ApiFactory;
import me.jokey.mvp.api.GankApi;
import me.jokey.mvp.mvp.contract.GankContract;
import me.jokey.mvp.mvp.ui.entity.gank.GankBean;
import me.jokey.mvp.mvp.ui.entity.gank.ResultBean;
import rx.Observable;

/**
 * Created by wz on 2017/6/22 14:24.
 * GankModel
 */


public class GankModel implements GankContract.Model {

    private GankApi gankApi = ApiFactory.getGankApiSingleton();

    @Override
    public Observable<GankBean<ResultBean>> getGank(String type, int count, int page) {
        return gankApi.getGank(type, count, page);
    }
}
