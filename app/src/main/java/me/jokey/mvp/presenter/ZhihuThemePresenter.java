package me.jokey.mvp.presenter;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import me.jokey.mvp.contract.ZhihuThemeContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuTheme;


/**
 * Created by wz on 2017/6/22 11:14.
 * ZhihuThemePresenter
 */

public class ZhihuThemePresenter extends ZhihuThemeContract.Presenter {

    @Override
    public void getZhihuThemes() {
        mModel.getZhihuThemes(new ResultCallBack<ZhihuTheme>() {
            @Override
            public void onFail(String error) {
                mView.onRequestError(error);
            }

            @Override
            public void onSuccessEntity(ZhihuTheme entity) {
                mView.loadZhihuThemes(entity.getOthers());
            }
        });
    }
}
