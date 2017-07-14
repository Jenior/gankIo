package me.jokey.mvp.contract;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.model.api.ZhihuApi;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;
import me.jokey.mvp.model.entity.zhihu.ZhihuDetail;

/**
 * Created by wz on 2017/6/21 15:48.
 * ZhihuDetailContract
 */

public interface ZhihuDetailContract {

    abstract class Model extends BaseModel<ZhihuApi> {
        public Model() {super(ZhihuApi.class);}

      public abstract void  getZhihuDetail(String id, ResultCallBack<ZhihuDetail> callBack);
    }

    interface View extends BaseView {
        void loadZhihuDetail(ZhihuDetail data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getZhihuDetail(String id);
    }

}
