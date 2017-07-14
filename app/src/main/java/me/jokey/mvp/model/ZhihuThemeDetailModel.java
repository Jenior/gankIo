package me.jokey.mvp.model;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;
import com.single.zuozuoyou.fuckrx.model.BaseObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jokey.mvp.contract.ZhihuThemeDetailContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuThemeDetail;

/**
 * Created by wz on 2017/6/21 15:51.
 * ZhihuDetailModel
 */


public class ZhihuThemeDetailModel extends ZhihuThemeDetailContract.Model {

    @Override
    public void getZhihuThemeDetail(String id, ResultCallBack<ZhihuThemeDetail> callBack) {
        api.getZhihuThemeDetail(id).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<ZhihuThemeDetail>() {
            @Override
            protected void onSuccess(ZhihuThemeDetail zhihuThemeDetail) {
                callBack.onSuccessEntity(zhihuThemeDetail);
            }

            @Override
            protected void onFail(String resultCode, String resultDesc) {
                callBack.onFail(resultDesc);
            }
        });
    }
}
