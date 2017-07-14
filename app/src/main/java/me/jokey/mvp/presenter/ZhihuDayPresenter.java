package me.jokey.mvp.presenter;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import me.jokey.mvp.contract.ZhihuDayContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.model.entity.zhihu.ZhihuDay;
import me.jokey.mvp.utils.LogUtils;


/**
 * Created by wz on 2017/6/20 16:57.
 * ZhihuDayPresenter
 */


public class ZhihuDayPresenter extends ZhihuDayContract.Presenter {

    private String date;

    @Override
    public void getZhihuLatest() {
        mModel.getZhihuLatest(new ResultCallBack<ZhihuDay>() {
            @Override
            public void onFail(String error) {
                mView.onRequestError(error);
            }

            @Override
            public void onSuccessEntity(ZhihuDay entity) {
                date = entity.getDate();
                mView.loadZhihuBanner(entity.getTop_stories());
                mView.loadZhihuLatest(entity.getStories());
            }
        });
    }

    @Override
    public void getZhihuBefore() {
        LogUtils.debugInfo("加载更多，当前日期：" + date);
        mModel.getZhihuBefore(date, new ResultCallBack<ZhihuBefore>() {
            @Override
            public void onFail(String error) {
                mView.onRequestError(error);
            }

            @Override
            public void onSuccessEntity(ZhihuBefore entity) {
                date = entity.getDate();
                mView.loadZhihuBefore(entity.getStories());
            }
        });
    }

}
