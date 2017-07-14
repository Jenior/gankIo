package me.jokey.mvp.contract;


import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import java.util.List;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.model.api.ZhihuApi;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;
import me.jokey.mvp.model.entity.zhihu.OtherBean;
import me.jokey.mvp.model.entity.zhihu.ZhihuTheme;

/**
 * Created by wz on 2017/6/22 11:08.
 * ZhihuThemeContract 主题日报Contract
 */

public interface ZhihuThemeContract {

  abstract class   Model extends BaseModel<ZhihuApi>{
      public Model() {
          super(ZhihuApi.class);
      }

     public abstract  void getZhihuThemes(ResultCallBack<ZhihuTheme> callBack);
    }

    interface View extends BaseView {
        void loadZhihuThemes(List<OtherBean> data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getZhihuThemes();
    }

}
