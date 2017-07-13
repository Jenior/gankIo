package me.jokey.mvp.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import me.jokey.mvp.R;
import me.jokey.mvp.contract.ZhihuThemeDetailContract;
import me.jokey.mvp.model.ZhihuThemeDetailModel;
import me.jokey.mvp.presenter.ZhihuThemeDetailPresenter;
import me.jokey.mvp.view.adapter.ZhihuThemeDetailsAdapter;
import me.jokey.mvp.model.entity.zhihu.EditorBean;
import me.jokey.mvp.model.entity.zhihu.StoriesBean;
import me.jokey.mvp.model.entity.zhihu.ZhihuThemeDetail;
import me.jokey.mvp.utils.LogUtils;
import me.jokey.mvp.utils.ToastUtils;

/**
 * Created by wz on 2017/6/22 11:46.
 * ZhihuThemeActivity
 */

public class ZhihuThemeActivity extends BaseFrameActivity<ZhihuThemeDetailPresenter, ZhihuThemeDetailModel>
        implements ZhihuThemeDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_background)
    ImageView mBackgroundView;
    @BindView(R.id.toolbar_avatar)
    ImageView mAvatarView;
    @BindView(R.id.toolbar_bio)
    TextView mBioView;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ZhihuThemeDetailsAdapter mAdapter;

    @Override
    protected int initView() {
        return R.layout.activity_zhihu_theme;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        collapsingToolbarLayout.setTitle(name);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //取消阴影
            getSupportActionBar().setElevation(0);
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            mToolbar.setNavigationOnClickListener(v -> finish());

            int statusBarHeight = 0;
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
            Log.d("wz", "状态栏高度 : " + statusBarHeight);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mToolbar.getLayoutParams();
            params.setMargins(0, statusBarHeight, 0, 0);
            mToolbar.setLayoutParams(params);
        }

        if (mAdapter == null) {
            mAdapter = new ZhihuThemeDetailsAdapter();
            mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((baseQuickAdapter, view, i) -> {
                StoriesBean data = mAdapter.getData().get(i);
                Intent intent = new Intent(this, ZhihuWebActivity.class);
                intent.putExtra("id", String.valueOf(data.getId()));
                intent.putExtra("title", data.getTitle());
                startActivity(intent);
            });
        }
        mPresenter.getZhihuThemeDetail(id);
    }

    @Override
    public void loadZhihuThemeDetail(ZhihuThemeDetail data) {
        Glide.with(this)
                .load(data.getBackground())
                .asBitmap()
                .crossFade()
                .into(mBackgroundView);

        List<EditorBean> editors = data.getEditors();
        Glide.with(this)
                .load(editors.get(0).getAvatar())
                .asBitmap()
                .crossFade()
                .into(mAvatarView);
        mBioView.setText(editors.get(0).getBio());
        mAdapter.setNewData(data.getStories());
    }

    @Override
    public void onRequestError(String msg) {
        super.onRequestError(msg);
        LogUtils.debugInfo(msg);
        ToastUtils.showToast(this, msg);
    }
}
