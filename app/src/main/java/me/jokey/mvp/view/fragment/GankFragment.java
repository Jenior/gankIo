package me.jokey.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.jokey.mvp.R;
import me.jokey.mvp.contract.GankContract;
import me.jokey.mvp.model.GankModel;
import me.jokey.mvp.presenter.GankPresenter;
import me.jokey.mvp.view.activity.GankWebActivity;
import me.jokey.mvp.view.activity.PictureViewPagerActivity;
import me.jokey.mvp.view.adapter.GankAdapter;
import me.jokey.mvp.model.entity.gank.ResultBean;
import me.jokey.mvp.utils.LogUtils;
import me.jokey.mvp.utils.ToastUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by wz on 2017/6/22 14:22.
 * GankFragment
 */

public class GankFragment extends BaseFrameFragment<GankPresenter, GankModel> implements
        GankContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;

    private String type;
    private GankAdapter mAdapter;

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
            } else onUserVisible();
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else onUserInvisible();
        }
    }

    /**
     * 第一次可见状态
     */
    private void onFirstUserVisible() {
        LogUtils.debugInfo("第一次可见状态");

        Bundle b = getArguments();
        type = b.getString("type");
        LogUtils.warnInfo(type);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        if (mAdapter == null) {
            if ("福利".equals(type)) {
                mAdapter = new GankAdapter(R.layout.item_recycle_meizi, 0);
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            } else {
                mAdapter = new GankAdapter(R.layout.item_recycle_gank, 1);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnLoadMoreListener(this, mRecyclerView);
            mAdapter.setOnItemClickListener((baseQuickAdapter, view, position) -> {
                ResultBean data = mAdapter.getData().get(position);
                if ("福利".equals(type)) {
                    PictureViewPagerActivity.start(getActivity(), position, (ArrayList<ResultBean>) mAdapter.getData());
                } else {
                    GankWebActivity.start(getActivity(), data.getUrl(), data.getDesc());
                }
            });
        }
        mPresenter.getGank(true, type);
    }

    /**
     * 第一次不可见状态
     */
    private void onFirstUserInvisible() {
        LogUtils.debugInfo("第一次不可见状态");
    }

    /**
     * 页面可见状态,类似于onStart()
     */
    private void onUserVisible() {
        LogUtils.debugInfo("页面可见状态,类似于onStart()");
    }

    /**
     * 页面不可见状态,类似于onStop()
     */
    private void onUserInvisible() {
        LogUtils.debugInfo("页面不可见状态,类似于onStop()");
    }

    @Override
    public void onRefresh() {
        mPresenter.getGank(true, type);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getGank(false, type);
    }

    @Override
    public void onRequestStart() {
        super.onRequestStart();
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flag -> {
                    if (mAdapter.getData().size() == 0) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        LogUtils.debugInfo("设置加载更多 false");
                        mAdapter.setEnableLoadMore(false);
                    }
                });
    }

    @Override
    public void onRequestEnd() {
        super.onRequestEnd();
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flag -> {
                    if (mSwipeRefreshLayout.isRefreshing())
                        mSwipeRefreshLayout.setRefreshing(false);
                    if (mAdapter.getData().size() > 0) {
                        LogUtils.debugInfo("设置加载更多 true");
                        mAdapter.setEnableLoadMore(true);
                    }
                });
    }

    @Override
    public void onRequestError(String msg) {
        super.onRequestError(msg);
        LogUtils.warnInfo(msg);
        ToastUtils.showToast(getContext(), msg);
        if (mAdapter.getData().size() > 0) {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void loadGank(boolean isRefresh, List<ResultBean> data) {
        if (data.size() > 0) {
            if (isRefresh) {
                mAdapter.setNewData(data);
            } else {
                mAdapter.addData(mAdapter.getData().size(), data);
            }
            mAdapter.loadMoreComplete();
        } else { // 请求到的数据为0条
            LogUtils.debugInfo("请求的数据条目为0");
            if (mAdapter.getData().size() == 0) {

            } else {
                mAdapter.loadMoreEnd();
            }
        }
    }
}
