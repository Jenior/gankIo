package me.jokey.mvp.contract;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import java.util.List;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.model.api.GankApi;
import me.jokey.mvp.model.entity.gank.GankBean;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;
import me.jokey.mvp.model.entity.gank.ResultBean;

/**
 * Created by wz on 2017/6/22 14:22.
 * GankContract
 */


public interface GankContract {

    abstract class Model extends BaseModel<GankApi> {
        public Model() {super(GankApi.class);}
        public abstract void getGank(String type, int count, int page, ResultCallBack<GankBean<ResultBean>> callBack);
    }

    interface View extends BaseView {
        void loadGank(boolean isRefresh, List<ResultBean> data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getGank(boolean isRefresh, String type);
    }

}
