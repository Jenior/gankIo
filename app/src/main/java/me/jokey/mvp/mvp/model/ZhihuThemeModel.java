package me.jokey.mvp.mvp.model;

import me.jokey.mvp.api.ApiFactory;
import me.jokey.mvp.api.ZhihuApi;
import me.jokey.mvp.mvp.contract.ZhihuThemeContract;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuTheme;
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
