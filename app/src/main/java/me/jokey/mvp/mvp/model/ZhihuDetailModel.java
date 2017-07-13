package me.jokey.mvp.mvp.model;

import me.jokey.mvp.api.ApiFactory;
import me.jokey.mvp.api.ZhihuApi;
import me.jokey.mvp.mvp.contract.ZhihuDetailContract;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuDetail;
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
