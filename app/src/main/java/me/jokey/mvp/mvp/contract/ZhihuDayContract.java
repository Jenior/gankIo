package me.jokey.mvp.mvp.contract;

import java.util.List;

import me.jokey.mvp.mvp.base.BaseModel;
import me.jokey.mvp.mvp.base.BasePresenter;
import me.jokey.mvp.mvp.base.BaseView;
import me.jokey.mvp.mvp.ui.entity.zhihu.StoriesBean;
import me.jokey.mvp.mvp.ui.entity.zhihu.TopStoriesBean;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuDay;
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
