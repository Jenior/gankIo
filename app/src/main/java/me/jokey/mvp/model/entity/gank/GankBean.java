package me.jokey.mvp.model.entity.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wz on 2017/6/22 14:13.
 * GankBean
 */


public class GankBean<T> implements Serializable{

    private boolean error;

    private List<T> results;

    public boolean isSuccess() {
        return !isError();
    }

    private boolean isError() {
        return error;
    }

    public List<T> getResults() {
        return results;
    }
}
