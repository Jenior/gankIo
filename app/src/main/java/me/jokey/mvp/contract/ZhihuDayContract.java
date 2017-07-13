package me.jokey.mvp.contract;

import java.util.List;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;
import me.jokey.mvp.model.entity.zhihu.StoriesBean;
import me.jokey.mvp.model.entity.zhihu.TopStoriesBean;
import me.jokey.mvp.model.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.model.entity.zhihu.ZhihuDay;
import rx.Observable;

/**
 * Created by wz on 2017/6/20 16:54.
 * ZhihuDayContract
 */

public interface ZhihuDayContract {

    interface Model extends BaseModel {
        Observable<ZhihuDay> getZhihuLatest();

        Observable<ZhihuBefore> getZhihuBefore(String date);
    }

    interface View extends BaseView {
        void loadZhihuBanner(List<TopStoriesBean> data);

        void loadZhihuLatest(List<StoriesBean> data);

        void loadZhihuBefore(List<StoriesBean> data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getZhihuLatest();

        public abstract void getZhihuBefore();
    }

}
