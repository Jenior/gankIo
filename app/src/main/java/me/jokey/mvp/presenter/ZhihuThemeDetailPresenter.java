package me.jokey.mvp.presenter;

import me.jokey.mvp.model.api.ApiSubscriber;
import me.jokey.mvp.contract.ZhihuThemeDetailContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuThemeDetail;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wz on 2017/6/22 11:14.
 * ZhihuThemePresenter
 */

public class ZhihuThemeDetailPresenter extends ZhihuThemeDetailContract.Presenter {

    @Override
    public void getZhihuThemeDetail(String id) {
        mRxManager.add(mModel.getZhihuThemeDetail(id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(() -> mView.onRequestStart())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> mView.onRequestEnd())
                .subscribe(new ApiSubscriber<ZhihuThemeDetail>() {
                    @Override
                    public void onNext(ZhihuThemeDetail data) {
                        super.onNext(data);
                        mView.loadZhihuThemeDetail(data);
                    }

                    @Override
                    public void doOnError(String error) {
                        super.doOnError(error);
                        mView.onRequestError(error);
                    }
                }));
    }
}
