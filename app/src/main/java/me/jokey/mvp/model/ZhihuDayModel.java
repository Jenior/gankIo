package me.jokey.mvp.model;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;
import com.single.zuozuoyou.fuckrx.model.BaseObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jokey.mvp.contract.ZhihuDayContract;
import me.jokey.mvp.model.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.model.entity.zhihu.ZhihuDay;

/**
 * Created by wz on 2017/6/20 16:59.
 * ZhihuDayModel
 */

public class ZhihuDayModel extends ZhihuDayContract.Model {


    public ZhihuDayModel() {
        super();
    }


    @Override
    public void getZhihuLatest(ResultCallBack<ZhihuDay> callBack) {
        api.getZhihuLatest().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<ZhihuDay>() {
            @Override
            protected void onSuccess(ZhihuDay zhihuDay) {
                callBack.onSuccessEntity(zhihuDay);
            }

            @Override
            protected void onFail(String resultCode, String resultDesc) {
                callBack.onFail(resultDesc);
            }
        });
    }

    @Override
    public void getZhihuBefore(String date, ResultCallBack<ZhihuBefore> callBack) {
        api.getZhihuBeforet(date).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<ZhihuBefore>() {

            @Override
            protected void onSuccess(ZhihuBefore zhihuBefore) {
                callBack.onSuccessEntity(zhihuBefore);
            }

            @Override
            protected void onFail(String resultCode, String resultDesc) {
                callBack.onFail(resultDesc);
            }
        });
    }
}
