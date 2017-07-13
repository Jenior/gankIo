package me.jokey.mvp.mvp.base;

import android.os.Bundle;

import me.jokey.mvp.common.BaseFragment;
import me.jokey.mvp.utils.TUtil;

/**
 * Created by wz on 2017/6/20 17:56.
 * BaseFrameFragment.java
 */

public abstract class BaseFrameFragment<P extends BasePresenter, M extends BaseModel> extends BaseFragment implements BaseView {

    public P mPresenter;

    public M mModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) {
            mPresenter.attachVM(this, mModel);
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachVM();
        }
        super.onDestroy();
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestError(String msg) {
    }

    @Override
    public void onInternetError() {

    }

    @Override
    public void onRequestEnd() {

    }
}
