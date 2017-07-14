package me.jokey.mvp.model;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;
import com.single.zuozuoyou.fuckrx.model.BaseObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jokey.mvp.contract.GankContract;
import me.jokey.mvp.model.entity.gank.GankBean;
import me.jokey.mvp.model.entity.gank.ResultBean;


/**
 * Created by wz on 2017/6/22 14:24.
 * GankModel
 */


public class GankModel extends GankContract.Model {



    @Override
    public void getGank(String type, int count, int page, ResultCallBack<GankBean<ResultBean>> callBack) {
        api.getGank(type,count,page).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<GankBean<ResultBean>>() {


            @Override
            protected void onSuccess(GankBean<ResultBean> resultBeanGankBean) {
                callBack.onSuccessEntity(resultBeanGankBean);
            }

            @Override
            protected void onFail(String resultCode, String resultDesc) {
                callBack.onFail(resultDesc);
            }
        });
    }


}
