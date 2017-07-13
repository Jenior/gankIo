package me.jokey.mvp.mvp.contract;

import java.util.List;

import me.jokey.mvp.mvp.base.BaseModel;
import me.jokey.mvp.mvp.base.BasePresenter;
import me.jokey.mvp.mvp.base.BaseView;
import me.jokey.mvp.mvp.ui.entity.zhihu.OtherBean;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuTheme;
import rx.Observable;

/**
 * Created by wz on 2017/6/22 11:08.
 * ZhihuThemeContract 主题日报Contract
 */

public interface ZhihuThemeContract {

    interface Model extends BaseModel {
        Observable<ZhihuTheme> getZhihuThemes();
    }

    interface View extends BaseView {
        void loadZhihuThemes(List<OtherBean> data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getZhihuThemes();
    }

}
