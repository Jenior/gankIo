package me.jokey.mvp.contract;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import java.util.List;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.model.api.ZhihuApi;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;
import me.jokey.mvp.model.entity.zhihu.StoriesBean;
import me.jokey.mvp.model.entity.zhihu.TopStoriesBean;
import me.jokey.mvp.model.entity.zhihu.ZhihuBefore;
import me.jokey.mvp.model.entity.zhihu.ZhihuDay;


/**
 * Created by wz on 2017/6/20 16:54.
 * ZhihuDayContract
 */

public interface ZhihuDayContract {

    abstract class Model extends BaseModel<ZhihuApi> {
        public Model() {
            super(ZhihuApi.class);
        }

        public abstract void getZhihuLatest(ResultCallBack<ZhihuDay> callBack);

        public abstract void getZhihuBefore(String date,ResultCallBack<ZhihuBefore> callBack);
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
