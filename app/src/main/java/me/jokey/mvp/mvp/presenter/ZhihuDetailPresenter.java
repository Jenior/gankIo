package me.jokey.mvp.mvp.presenter;

import me.jokey.mvp.api.ApiSubscriber;
import me.jokey.mvp.mvp.contract.ZhihuDetailContract;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuDetail;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wz on 2017/6/21 15:53.
 * ZhihuDetailPresenter
 */

public class ZhihuDetailPresenter extends ZhihuDetailContract.Presenter {

    @Override
    public void getZhihuDetail(String id) {
        mRxManager.add(mModel.getZhihuDetail(id)
                .subscribeOn(Schedulers.io())
                .doOnCompleted(() -> mView.onRequestStart())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> mView.onRequestEnd())
                .subscribe(new ApiSubscriber<ZhihuDetail>() {

                    @Override
                    public void onNext(ZhihuDetail data) {
                        super.onNext(data);
                        mView.loadZhihuDetail(data);
                    }

                    @Override
                    public void doOnError(String error) {
                        super.doOnError(error);
                        mView.onRequestError(error);
                    }
                }));
    }
}
