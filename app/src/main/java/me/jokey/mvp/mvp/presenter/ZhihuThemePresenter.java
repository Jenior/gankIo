package me.jokey.mvp.mvp.presenter;

import me.jokey.mvp.api.ApiSubscriber;
import me.jokey.mvp.mvp.contract.ZhihuThemeContract;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuTheme;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wz on 2017/6/22 11:14.
 * ZhihuThemePresenter
 */

public class ZhihuThemePresenter extends ZhihuThemeContract.Presenter {

    @Override
    public void getZhihuThemes() {
        mRxManager.add(mModel.getZhihuThemes()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(() -> mView.onRequestStart())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> mView.onRequestEnd())
                .subscribe(new ApiSubscriber<ZhihuTheme>() {
                    @Override
                    public void onNext(ZhihuTheme zhihuTheme) {
                        super.onNext(zhihuTheme);
                        mView.loadZhihuThemes(zhihuTheme.getOthers());
                    }

                    @Override
                    public void doOnError(String error) {
                        super.doOnError(error);
                        mView.onRequestError(error);
                    }
                }));
    }
}
