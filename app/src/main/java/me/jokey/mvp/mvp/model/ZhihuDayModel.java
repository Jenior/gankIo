package me.jokey.mvp.mvp.model;

import me.jokey.mvp.api.ApiFactory;
import me.jokey.mvp.api.ZhihuApi;
import me.jokey.mvp.mvp.contract.ZhihuDayContract;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuDay;
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
