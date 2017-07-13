package me.jokey.mvp.mvp.contract;

import me.jokey.mvp.mvp.base.BaseModel;
import me.jokey.mvp.mvp.base.BasePresenter;
import me.jokey.mvp.mvp.base.BaseView;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuThemeDetail;
import rx.Observable;

/**
 * Created by wz on 2017/6/22 12:01.
 * ZhihuThemeDetailContract
 */

public interface ZhihuThemeDetailContract {

    interface Model extends BaseModel {
        Observable<ZhihuThemeDetail> getZhihuThemeDetail(String id);
    }

    interface View extends BaseView {
        void loadZhihuThemeDetail(ZhihuThemeDetail data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getZhihuThemeDetail(String id);
    }

}
