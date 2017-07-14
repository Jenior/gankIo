package com.single.zuozuoyou.fuckrx.model;

import android.content.Context;
import android.net.ParseException;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.internal.$Gson$Types;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.single.zuozuoyou.fuckrx.widget.WaitDialog;

import org.json.JSONException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.internal.observers.BlockingBaseObserver;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by zuozuoyou on 2017/4/28.
 */

public abstract class BaseObserver<T extends Object> extends BlockingBaseObserver<Response<ResponseBody>> {
    private Type mType;
    private WaitDialog mWaitDialog;

    public BaseObserver() {
        mType = getSuperclassTypeParameter(getClass());
    }

    public BaseObserver(Context context) {
        mType = getSuperclassTypeParameter(getClass());
        initWaitDialog(context);
    }

    /**
     * 初始化 dialog
     */
    private void initWaitDialog(Context mContext) {
        mWaitDialog = new WaitDialog(mContext);
        mWaitDialog.setMessage("加载中...");
        mWaitDialog.show();
    }

    Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterizedType
                .getActualTypeArguments()[0]);
    }

    protected abstract void onSuccess(T t);
    protected  void onSuccess(ResponseBody responseBody){}
    protected abstract void onFail(String resultCode, String resultDesc);

    @Override
    public void onNext(Response<ResponseBody> value) {
        Gson gson = new Gson();
        String result;
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mWaitDialog != null) {
                        mWaitDialog.dismiss();
                    }
                }
            }, 0);

            if (mType instanceof ResponseBody){
                onSuccess(value.body());
                return;
            }
            result = value.body().string();
            Log.e("entity", "entity" + result);
            T t = gson.fromJson(result, mType);
            onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
            onFail("500", e.getMessage());
        }

    }

    @Override
    public void onError(Throwable t) {
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
        onFail("500", msg);
        t.printStackTrace();
    }
}
