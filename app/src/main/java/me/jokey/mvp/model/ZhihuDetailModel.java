package me.jokey.mvp.model;

import me.jokey.mvp.model.api.ApiFactory;
import me.jokey.mvp.model.api.ZhihuApi;
import me.jokey.mvp.contract.ZhihuDetailContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuDetail;
import rx.Observable;

/**
 * Created by wz on 2017/6/21 15:51.
 * ZhihuDetailModel
 */


public class ZhihuDetailModel implements ZhihuDetailContract.Model{

    private ZhihuApi mZhihuApi = ApiFactory.getZhihuApiSingleton();

    @Override
    public Observable<ZhihuDetail> getZhihuDetail(String id) {
        return mZhihuApi.getZhihuDetail(id);
    }
}
