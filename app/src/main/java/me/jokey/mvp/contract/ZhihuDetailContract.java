package me.jokey.mvp.contract;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;
import me.jokey.mvp.model.entity.zhihu.ZhihuDetail;
import rx.Observable;

/**
 * Created by wz on 2017/6/21 15:48.
 * ZhihuDetailContract
 */

public interface ZhihuDetailContract {

    interface Model extends BaseModel {
        Observable<ZhihuDetail> getZhihuDetail(String id);
    }

    interface View extends BaseView {
        void loadZhihuDetail(ZhihuDetail data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getZhihuDetail(String id);
    }

}
