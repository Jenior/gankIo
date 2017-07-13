package me.jokey.mvp.mvp.presenter;

import me.jokey.mvp.api.ApiSubscriber;
import me.jokey.mvp.mvp.contract.GankContract;
import me.jokey.mvp.mvp.ui.entity.gank.GankBean;
import me.jokey.mvp.mvp.ui.entity.gank.ResultBean;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wz on 2017/6/22 14:24.
 * GankPresenter
 */

public class GankPresenter extends GankContract.Presenter {

    private static final int COUNT = 15;
    private int page = 1;

    @Override
    public void getGank(boolean isRefresh, String type) {

        if (isRefresh) page = 1;
        else page++;

        mRxManager.add(mModel.getGank(type, COUNT, page)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(() -> mView.onRequestStart())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> mView.onRequestEnd())
                .subscribe(new ApiSubscriber<GankBean<ResultBean>>() {

                    @Override
                    public void onNext(GankBean<ResultBean> resultBeanGankBean) {
                        super.onNext(resultBeanGankBean);
                        if (resultBeanGankBean.isSuccess())
                            mView.loadGank(isRefresh, resultBeanGankBean.getResults());
                    }

                    @Override
                    public void doOnError(String error) {
                        super.doOnError(error);
                        mView.onRequestError(error);
                    }
                }));


    }
}
