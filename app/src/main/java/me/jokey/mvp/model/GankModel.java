package me.jokey.mvp.model;

import me.jokey.mvp.model.api.ApiFactory;
import me.jokey.mvp.model.api.GankApi;
import me.jokey.mvp.contract.GankContract;
import me.jokey.mvp.model.entity.gank.GankBean;
import me.jokey.mvp.model.entity.gank.ResultBean;
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
