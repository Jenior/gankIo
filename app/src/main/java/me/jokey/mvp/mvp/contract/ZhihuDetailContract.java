package me.jokey.mvp.mvp.contract;

import me.jokey.mvp.mvp.base.BaseModel;
import me.jokey.mvp.mvp.base.BasePresenter;
import me.jokey.mvp.mvp.base.BaseView;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuDetail;
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
