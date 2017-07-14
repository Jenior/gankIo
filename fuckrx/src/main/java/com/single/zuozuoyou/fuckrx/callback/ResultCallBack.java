package com.single.zuozuoyou.fuckrx.callback;

import java.util.List;

/**
 * Created by lenovo on 2017/5/8.
 */

public abstract class ResultCallBack<T> {
    public void onSuccessEntity(T t){};

    public  void onSuccessList(List<T> list){};

    public  void onSuccessStr(String msg){};

    public void onFail(String msg){};
}
