package me.jokey.mvp.presenter;

import me.jokey.mvp.model.api.ApiSubscriber;
import me.jokey.mvp.contract.ZhihuDayContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.model.entity.zhihu.ZhihuDay;
import me.jokey.mvp.utils.LogUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wz on 2017/6/20 16:57.
 * ZhihuDayPresenter
 */


public class ZhihuDayPresenter extends ZhihuDayContract.Presenter {

    private String date;

    @Override
    public void getZhihuLatest() {
        mRxManager.add(mModel.getZhihuLatest()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(() -> {
                    LogUtils.debugInfo("doOnSubscribe()");
                    mView.onRequestStart();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    LogUtils.debugInfo("doAfterTerminate()");
                    mView.onRequestEnd();
                })
                .subscribe(new ApiSubscriber<ZhihuDay>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void doOnError(String error) {
                        super.doOnError(error);
                        mView.onRequestError(error);
                    }

                    @Override
                    public void onNext(ZhihuDay day) {
                        date = day.getDate();
                        mView.loadZhihuBanner(day.getTop_stories());
                        mView.loadZhihuLatest(day.getStories());
                    }
                }));
    }

    @Override
    public void getZhihuBefore() {
        LogUtils.debugInfo("加载更多，当前日期：" + date);
        mRxManager.add(mModel.getZhihuBefore(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<ZhihuBefore>() {

                    @Override
                    public void onNext(ZhihuBefore data) {
                        super.onNext(data);
                        date = data.getDate();
                        mView.loadZhihuBefore(data.getStories());
                    }

                    @Override
                    public void doOnError(String error) {
                        super.doOnError(error);
                        mView.onRequestError(error);
                    }
                }));
    }

}
