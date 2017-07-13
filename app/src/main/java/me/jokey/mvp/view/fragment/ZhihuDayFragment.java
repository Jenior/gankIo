package me.jokey.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.jokey.mvp.R;
import me.jokey.mvp.contract.ZhihuDayContract;
import me.jokey.mvp.model.ZhihuDayModel;
import me.jokey.mvp.presenter.ZhihuDayPresenter;
import me.jokey.mvp.view.activity.ZhihuWebActivity;
import me.jokey.mvp.view.adapter.ZhihuDayAdapter;
import me.jokey.mvp.model.entity.zhihu.StoriesBean;
import me.jokey.mvp.model.entity.zhihu.TopStoriesBean;
import me.jokey.mvp.utils.LogUtils;
import me.jokey.mvp.utils.ToastUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by wz on 2017/6/20 14:44.
 * ZhihuDayFragment 知乎日报
 */

public class ZhihuDayFragment extends BaseFrameFragment<ZhihuDayPresenter, ZhihuDayModel> implements
        ZhihuDayContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;

    private ZhihuDayAdapter mAdapter;
    private View mHeaderView;

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
        LogUtils.debugInfo("第一次可见状态");

        mSwipeRefreshLayout.setOnRefreshListener(this);
        if (mAdapter == null) {
            mAdapter = new ZhihuDayAdapter();
            mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnLoadMoreListener(this, mRecyclerView);
            mAdapter.setOnItemClickListener((baseQuickAdapter, view, position) -> {
                StoriesBean data = mAdapter.getData().get(position);
                Intent intent = new Intent(getActivity(), ZhihuWebActivity.class);
                intent.putExtra("id", String.valueOf(data.getId()));
                intent.putExtra("title", data.getTitle());
                getActivity().startActivity(intent);
            });
            mHeaderView = getActivity().getLayoutInflater().inflate(R.layout.header_banner, mRecyclerView, false);
        }
        mPresenter.getZhihuLatest();
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
    public void loadZhihuBanner(List<TopStoriesBean> data) {
        if (mAdapter.getHeaderLayoutCount() == 0 && mHeaderView != null) {
            mAdapter.addHeaderView(mHeaderView);
            List<String> mImageList = new ArrayList<>();
            List<String> mTitleList = new ArrayList<>();
            for (TopStoriesBean bean : data) {
                mImageList.add(bean.getImage());
                mTitleList.add(bean.getTitle());
            }
            Banner banner = (Banner) mHeaderView.findViewById(R.id.headerBanner);
            banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setImageLoader(new GlideImageLoader())
                    .setIndicatorGravity(BannerConfig.RIGHT)
                    .setImages(mImageList)
                    .setBannerTitles(mTitleList)
                    .isAutoPlay(true)
                    .setDelayTime(3000)
                    .setOnBannerListener(position -> {
                        TopStoriesBean topStoriesBean = data.get(position);
                        Intent intent = new Intent(getActivity(), ZhihuWebActivity.class);
                        intent.putExtra("id", String.valueOf(topStoriesBean.getId()));
                        intent.putExtra("title", topStoriesBean.getTitle());
                        getActivity().startActivity(intent);
                    })
                    .start();
        }
    }

    @Override
    public void loadZhihuLatest(List<StoriesBean> data) {
        LogUtils.debugInfo(data.toString());
        if (data.size() > 0) {
            mAdapter.setNewData(data);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void loadZhihuBefore(List<StoriesBean> data) {
        LogUtils.debugInfo(data.toString());
        if (data.size() > 0) {
            mAdapter.addData(mAdapter.getData().size(), data);
            mAdapter.loadMoreComplete();
        } else mAdapter.loadMoreEnd();
    }

    @Override
    public void onRefresh() {
        mPresenter.getZhihuLatest();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getZhihuBefore();
    }

    @Override
    public void onRequestStart() {
        super.onRequestStart();
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flag -> {
                    mSwipeRefreshLayout.setRefreshing(true);
                    LogUtils.debugInfo("设置加载更多 false");
                    mAdapter.setEnableLoadMore(false);
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
        mAdapter.loadMoreFail();
    }

    private final class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .crossFade()
                    .into(imageView);
        }
    }
}
