package me.jokey.mvp.presenter;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import me.jokey.mvp.contract.GankContract;
import me.jokey.mvp.model.entity.gank.GankBean;
import me.jokey.mvp.model.entity.gank.ResultBean;


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
        mModel.getGank(type, COUNT, page, new ResultCallBack<GankBean<ResultBean>>() {
            @Override
            public void onFail(String error) {
                mView.onRequestError(error);
            }

            @Override
            public void onSuccessEntity(GankBean<ResultBean> entity) {
                mView.loadGank(isRefresh, entity.getResults());
            }

        });
    }
}
