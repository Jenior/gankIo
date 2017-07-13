package me.jokey.mvp.mvp.presenter;

import android.content.Intent;
import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import me.jokey.mvp.api.ApiSubscriber;
import me.jokey.mvp.mvp.contract.DownImageContract;
import me.jokey.mvp.utils.LogUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wz on 2017/6/22 18:00.
 * DownImagePresenter
 */


public class DownImagePresenter extends DownImageContract.Presenter {

    @Override
    public void getGankImage(File file, String url) {
        mRxManager.add(mModel.getGankImage(url)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(() -> mView.onRequestStart())
                .map(responseBody -> {
                    InputStream is = responseBody.byteStream();
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = bis.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                        fos.flush();
                        fos.close();
                        bis.close();
                        is.close();
                        return file;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> mView.onRequestEnd())
                .subscribe(new ApiSubscriber<File>() {
                    @Override
                    public void onNext(File file) {
                        super.onNext(file);
                        if (file.exists()) {
                            LogUtils.debugInfo("" + file.toString());
                            mView.loadFile(file);
                        } else {
                            LogUtils.warnInfo("文件不存在");
                        }
                    }

                    @Override
                    public void doOnError(String error) {
                        super.doOnError(error);
                        mView.onRequestError(error);
                    }
                }));
    }
}
