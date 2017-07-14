package me.jokey.mvp.contract;


import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import java.io.File;

import me.jokey.mvp.model.BaseModel;
import me.jokey.mvp.model.api.GankApi;
import me.jokey.mvp.presenter.BasePresenter;
import me.jokey.mvp.view.interfaceView.BaseView;


/**
 * Created by wz on 2017/6/22 17:57.
 * DownImageContract
 */


public interface DownImageContract {

    abstract class Model extends BaseModel<GankApi> {
        public Model() {super(GankApi.class);}
        public abstract void getGankImage(String url,File file,ResultCallBack<File> callBack);
    }

    interface View extends BaseView {
        void loadFile(File file);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getGankImage(File file, String url);
    }

}
