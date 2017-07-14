package me.jokey.mvp.contract;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.model.api.ZhihuApi;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;
import me.jokey.mvp.model.entity.zhihu.ZhihuThemeDetail;

/**
 * Created by wz on 2017/6/22 12:01.
 * ZhihuThemeDetailContract
 */

public interface ZhihuThemeDetailContract {

    abstract class  Model extends BaseModel<ZhihuApi> {
        public Model() {
            super(ZhihuApi.class);
        }

      public abstract void getZhihuThemeDetail(String id, ResultCallBack<ZhihuThemeDetail> callBack);
    }

    interface View extends BaseView {
        void loadZhihuThemeDetail(ZhihuThemeDetail data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getZhihuThemeDetail(String id);
    }

}
