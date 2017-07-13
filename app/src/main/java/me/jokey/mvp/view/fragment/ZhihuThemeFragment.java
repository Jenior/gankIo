package me.jokey.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import me.jokey.mvp.R;
import me.jokey.mvp.contract.ZhihuThemeContract;
import me.jokey.mvp.model.ZhihuThemeModel;
import me.jokey.mvp.presenter.ZhihuThemePresenter;
import me.jokey.mvp.view.activity.ZhihuThemeActivity;
import me.jokey.mvp.view.adapter.ZhihuThemeAdapter;
import me.jokey.mvp.model.entity.zhihu.OtherBean;
import me.jokey.mvp.utils.LogUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by wz on 2017/6/20 14:44.
 * ZhihuDayFragment 知乎日报，主题
 */

public class ZhihuThemeFragment extends BaseFrameFragment<ZhihuThemePresenter, ZhihuThemeModel>
        implements ZhihuThemeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;

    private ZhihuThemeAdapter mAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_recycler, container, false);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        initPrepare();
    }

    private synchronized void initPrepare() {
        if (isPrepared) onFirstUserVisible();
        else isPrepared = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    /**
     * 第一次可见状态
     */
    private void onFirstUserVisible() {
        mPresenter.getZhihuThemes();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        if (mAdapter == null) {
            mAdapter = new ZhihuThemeAdapter();
            mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((baseQuickAdapter, view, position) -> {
                OtherBean data = mAdapter.getData().get(position);
                Intent intent = new Intent(getActivity(), ZhihuThemeActivity.class);
                intent.putExtra("id", String.valueOf(data.getId()));
                intent.putExtra("name", data.getName());
                getActivity().startActivity(intent);
            });
        }
    }

    /**
     * 第一次不可见状态
     */
    private void onFirstUserInvisible() {

    }

    /**
     * 页面可见状态,类似于onStart()
     */
    private void onUserVisible() {

    }

    /**
     * 页面不可见状态,类似于onStop()
     */
    private void onUserInvisible() {

    }

    @Override
    public void loadZhihuThemes(List<OtherBean> data) {
        LogUtils.debugInfo(data.toString());
        mAdapter.setNewData(data);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        mPresenter.getZhihuThemes();
    }

    @Override
    public void onRequestStart() {
        super.onRequestStart();
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flag -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void onRequestEnd() {
        super.onRequestEnd();
        // 此方法在主线程中执行，但是onRequestStart()确实在异步线程中执行
        // 为了确保onRequestEnd()后执行，建议下列代码加个异步
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flag -> {
                    if (mSwipeRefreshLayout.isRefreshing())
                        mSwipeRefreshLayout.setRefreshing(false);
                });
    }
}
