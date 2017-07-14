package me.jokey.mvp.presenter;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import me.jokey.mvp.contract.ZhihuDetailContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuDetail;

/**
 * Created by wz on 2017/6/21 15:53.
 * ZhihuDetailPresenter
 */

public class ZhihuDetailPresenter extends ZhihuDetailContract.Presenter {

    @Override
    public void getZhihuDetail(String id) {
        mModel.getZhihuDetail(id, new ResultCallBack<ZhihuDetail>() {
            @Override
            public void onFail(String error) {
                mView.onRequestError(error);
            }

            @Override
            public void onSuccessEntity(ZhihuDetail entity) {
                mView.loadZhihuDetail(entity);
            }
        });
    }
}
