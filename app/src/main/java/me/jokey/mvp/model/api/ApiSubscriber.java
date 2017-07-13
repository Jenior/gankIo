package me.jokey.mvp.model.api;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import me.jokey.mvp.utils.LogUtils;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by wz on 2017/6/21 13:30.
 * ApiSubscriber Subscriber异常处理
 */

public class ApiSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable t) {
        LogUtils.warnInfo(t.getClass().toString());
        String msg = "未知错误";
        if (t instanceof UnknownHostException) {
            msg = "网络不可用";
        } else if (t instanceof SocketTimeoutException) {
            msg = "请求网络超时";
        } else if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            if (httpException.code() == 500) {
                msg = "服务器发生错误";
            } else if (httpException.code() == 404) {
                msg = "请求地址不存在";
            } else if (httpException.code() == 403) {
                msg = "请求被服务器拒绝";
            } else if (httpException.code() == 307) {
                msg = "请求被重定向到其他页面";
            } else {
                msg = httpException.message();
            }
        } else if (t instanceof JsonParseException || t instanceof ParseException || t instanceof JSONException) {
            msg = "数据解析错误";
        }
        doOnError(msg);
    }

    public void doOnError(String error) {

    }

    @Override
    public void onNext(T t) {

    }
}
