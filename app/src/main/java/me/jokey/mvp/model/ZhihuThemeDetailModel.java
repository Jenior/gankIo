package me.jokey.mvp.model;

import me.jokey.mvp.model.api.ApiFactory;
import me.jokey.mvp.model.api.ZhihuApi;
import me.jokey.mvp.contract.ZhihuThemeDetailContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuThemeDetail;
import rx.Observable;

/**
 * Created by wz on 2017/6/21 15:51.
 * ZhihuDetailModel
 */


public class ZhihuThemeDetailModel implements ZhihuThemeDetailContract.Model {

    private ZhihuApi mZhihuApi = ApiFactory.getZhihuApiSingleton();

    @Override
    public Observable<ZhihuThemeDetail> getZhihuThemeDetail(String id) {
        return mZhihuApi.getZhihuThemeDetail(id);
    }
}
