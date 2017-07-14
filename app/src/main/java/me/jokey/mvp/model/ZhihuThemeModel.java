package me.jokey.mvp.model;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;
import com.single.zuozuoyou.fuckrx.model.BaseObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jokey.mvp.contract.ZhihuThemeContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuTheme;

/**
 * Created by wz on 2017/6/21 15:51.
 * ZhihuDetailModel
 */


public class ZhihuThemeModel extends ZhihuThemeContract.Model {

    @Override
    public void getZhihuThemes(ResultCallBack<ZhihuTheme> callBack) {
        api.getZhihuThemes().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<ZhihuTheme>() {
            @Override
            protected void onSuccess(ZhihuTheme zhihuTheme) {
                callBack.onSuccessEntity(zhihuTheme);
            }

            @Override
            protected void onFail(String resultCode, String resultDesc) {
                callBack.onFail(resultDesc);
            }
        });
    }
}
