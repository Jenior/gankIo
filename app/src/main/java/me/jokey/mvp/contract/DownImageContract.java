package me.jokey.mvp.contract;

import java.io.File;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by wz on 2017/6/22 17:57.
 * DownImageContract
 */


public interface DownImageContract {

    interface Model extends BaseModel {
        Observable<ResponseBody> getGankImage(String url);
    }

    interface View extends BaseView {
        void loadFile(File file);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getGankImage(File file, String url);
    }

}
