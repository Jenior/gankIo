package me.jokey.mvp.model;

import me.jokey.mvp.model.api.ApiFactory;
import me.jokey.mvp.model.api.ZhihuApi;
import me.jokey.mvp.contract.ZhihuDayContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.model.entity.zhihu.ZhihuDay;
import rx.Observable;

/**
 * Created by wz on 2017/6/20 16:59.
 * ZhihuDayModel
 */

public class ZhihuDayModel implements ZhihuDayContract.Model {

    ZhihuApi mZhihuApi = ApiFactory.getZhihuApiSingleton();

    @Override
    public Observable<ZhihuDay> getZhihuLatest() {
        return mZhihuApi.getZhihuLatest();
    }

    @Override
    public Observable<ZhihuBefore> getZhihuBefore(String date) {
        return mZhihuApi.getZhihuBeforet(date);
    }
}
