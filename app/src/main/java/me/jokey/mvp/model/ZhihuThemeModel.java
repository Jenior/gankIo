package me.jokey.mvp.model;

import me.jokey.mvp.model.api.ApiFactory;
import me.jokey.mvp.model.api.ZhihuApi;
import me.jokey.mvp.contract.ZhihuThemeContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuTheme;
import rx.Observable;

/**
 * Created by wz on 2017/6/21 15:51.
 * ZhihuDetailModel
 */


public class ZhihuThemeModel implements ZhihuThemeContract.Model {

    private ZhihuApi mZhihuApi = ApiFactory.getZhihuApiSingleton();

    @Override
    public Observable<ZhihuTheme> getZhihuThemes() {
        return mZhihuApi.getZhihuThemes();
    }
}
