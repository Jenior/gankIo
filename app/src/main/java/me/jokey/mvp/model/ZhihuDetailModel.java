package me.jokey.mvp.model;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;
import com.single.zuozuoyou.fuckrx.model.BaseObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jokey.mvp.contract.ZhihuDetailContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuDetail;

/**
 * Created by wz on 2017/6/21 15:51.
 * ZhihuDetailModel
 */


public class ZhihuDetailModel extends ZhihuDetailContract.Model{


    public ZhihuDetailModel() {
        super();
    }


    @Override
    public void getZhihuDetail(String id, ResultCallBack<ZhihuDetail> callBack) {
        api.getZhihuDetail(id).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<ZhihuDetail>() {
            @Override
            protected void onSuccess(ZhihuDetail zhihuDetail) {
                callBack.onSuccessEntity(zhihuDetail);
            }

            @Override
            protected void onFail(String resultCode, String resultDesc) {
                callBack.onFail(resultDesc);
            }
        });
    }
}
