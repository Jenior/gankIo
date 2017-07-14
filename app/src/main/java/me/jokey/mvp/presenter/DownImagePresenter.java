package me.jokey.mvp.presenter;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;

import java.io.File;
import me.jokey.mvp.contract.DownImageContract;
import me.jokey.mvp.utils.LogUtils;

/**
 * Created by wz on 2017/6/22 18:00.
 * DownImagePresenter
 */


public class DownImagePresenter extends DownImageContract.Presenter {

    @Override
    public void getGankImage(File file, String url) {
        mModel.getGankImage(url, file, new ResultCallBack<File>() {
            @Override
            public void onSuccessEntity(File entity) {
                if (entity.exists()) {
                    LogUtils.debugInfo("" + entity.toString());
                    mView.loadFile(entity);
                } else {
                    LogUtils.warnInfo("文件不存在");
                }
            }

            @Override
            public void onFail(String error) {
                mView.onRequestError(error);
            }
        });
    }
}
