package me.jokey.mvp.mvp.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import me.jokey.mvp.R;
import me.jokey.mvp.mvp.base.BaseFrameActivity;
import me.jokey.mvp.mvp.contract.ZhihuDetailContract;
import me.jokey.mvp.mvp.model.ZhihuDetailModel;
import me.jokey.mvp.mvp.presenter.ZhihuDetailPresenter;
import me.jokey.mvp.mvp.ui.entity.zhihu.ZhihuDetail;
import me.jokey.mvp.utils.HtmlUtil;
import me.jokey.mvp.utils.LogUtils;
import me.jokey.mvp.utils.ToastUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by wz on 2017/6/21 14:47.
 * ZhihuWebActivity
 */

public class ZhihuWebActivity extends BaseFrameActivity<ZhihuDetailPresenter, ZhihuDetailModel> implements
        ZhihuDetailContract.View {

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_imageView)
    ImageView mImageView;
    @BindView(R.id.toolbar_title)
    TextView mTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private String shareUrl;

    @Override
    protected int initView() {
        return R.layout.activity_zhihu_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        String id = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");

        mCollapsingToolbarLayout.setTitle(title);
        mTextView.setText(title);

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
            LogUtils.debugInfo("状态栏高度 : " + statusBarHeight);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mToolbar.getLayoutParams();
            params.setMargins(0, statusBarHeight, 0, 0);
            mToolbar.setLayoutParams(params);
        }

        WebSettings settings = mWebView.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mPresenter.getZhihuDetail(id);
    }

    @Override
    public void loadZhihuDetail(ZhihuDetail data) {
        shareUrl = data.getShare_url();

        String htmlData = HtmlUtil.createHtmlData(data.getBody(), data.getCss(), data.getJs());
        mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
        String imgUrl;
        try {
            imgUrl = data.getImages().get(0);
        } catch (Exception e) {
            imgUrl = data.getTheme().getThumbnail();
        }
        Glide.with(this)
                .load(imgUrl)
                .asBitmap()
                .crossFade()
                .into(mImageView);
    }

    @Override
    public void onRequestStart() {
        super.onRequestStart();
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((i) -> mProgressBar.setVisibility(View.VISIBLE));
    }

    @Override
    public void onRequestEnd() {
        super.onRequestEnd();
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((i) -> mProgressBar.setVisibility(View.GONE));
    }

    @Override
    public void onRequestError(String msg) {
        super.onRequestError(msg);
        LogUtils.debugInfo(msg);
        ToastUtils.showToast(this, msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                if (TextUtils.isEmpty(shareUrl))
                    mPresenter.getZhihuDetail(getIntent().getStringExtra("id"));
//                mWebView.reload();
                return true;
            case R.id.action_copy:
                ClipboardManager clipboardManager = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("URL", URL);
                clipboardManager.setPrimaryClip(clipData);
                ToastUtils.showToast(this, "链接已复制到粘贴板");
                break;
            case R.id.action_share:
                if (!TextUtils.isEmpty(shareUrl)) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Intent.EXTRA_TEXT, shareUrl);
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, "分享到..."));
                } else {
                    ToastUtils.showToast(this, "网页加载中，请稍候...");
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (mWebView != null) mWebView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        if (mWebView != null) mWebView.onResume();
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  在 Activity 销毁（ WebView ）的时候，先让 WebView 加载null内容，然后移除 WebView，再销毁 WebView，最后置空
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        this.shareUrl = null;
    }
}
