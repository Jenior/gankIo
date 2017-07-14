package me.jokey.mvp.presenter;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import me.jokey.mvp.contract.ZhihuThemeDetailContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuThemeDetail;

/**
 * Created by wz on 2017/6/22 11:14.
 * ZhihuThemePresenter
 */

public class ZhihuThemeDetailPresenter extends ZhihuThemeDetailContract.Presenter {

    @Override
    public void getZhihuThemeDetail(String id) {
        mModel.getZhihuThemeDetail(id, new ResultCallBack<ZhihuThemeDetail>() {
            @Override
            public void onFail(String error) {
                mView.onRequestError(error);
            }

            @Override
            public void onSuccessEntity(ZhihuThemeDetail entity) {
                mView.loadZhihuThemeDetail(entity);
            }
        });
    }
}
