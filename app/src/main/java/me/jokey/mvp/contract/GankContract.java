package me.jokey.mvp.contract;

import java.util.List;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;
import me.jokey.mvp.model.entity.gank.GankBean;
import me.jokey.mvp.model.entity.gank.ResultBean;
import rx.Observable;

/**
 * Created by wz on 2017/6/22 14:22.
 * GankContract
 */


public interface GankContract {

    interface Model extends BaseModel {
        Observable<GankBean<ResultBean>> getGank(String type, int count, int page);
    }

    interface View extends BaseView {
        void loadGank(boolean isRefresh, List<ResultBean> data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getGank(boolean isRefresh, String type);
    }

}
