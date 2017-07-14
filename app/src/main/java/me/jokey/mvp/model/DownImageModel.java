package me.jokey.mvp.model;

import com.single.zuozuoyou.fuckrx.callback.ResultCallBack;
import com.single.zuozuoyou.fuckrx.model.BaseObserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jokey.mvp.contract.DownImageContract;
import okhttp3.ResponseBody;


/**
 * Created by wz on 2017/6/22 17:58.
 * DownImageModel
 */


public class DownImageModel extends DownImageContract.Model {

    public DownImageModel() {
        super();
    }

    @Override
    public void getGankImage(String url, File file, ResultCallBack<File> callBack) {
        api.getGankImage(url).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<ResponseBody>() {
            @Override
            protected void onSuccess(ResponseBody responseBody) {
                try {
                    InputStream is = responseBody.byteStream();
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
                    callBack.onSuccessEntity(file);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected void onFail(String resultCode, String resultDesc) {
                callBack.onFail(resultDesc);
            }
        });
    }


}
