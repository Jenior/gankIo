package me.jokey.mvp.view.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import me.jokey.mvp.R;
import me.jokey.mvp.view.widget.MyScrollWebView;
import me.jokey.mvp.utils.NetWorkUtil;
import me.jokey.mvp.utils.ToastUtils;

import static me.jokey.mvp.R.id.toolbar;

public class GankWebActivity extends BaseActivity {

    private static final String GANK_URL = "GANK_URL";
    private static final String GANK_TITLE = "GANK_TITLE";

    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(R.id.web_view)
    MyScrollWebView mWebView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private String URL, TITLE;

    public static void start(Context context, String extraURL, String extraTitle) {
        Intent intent = new Intent(context, GankWebActivity.class);
        intent.putExtra(GANK_URL, extraURL);
        intent.putExtra(GANK_TITLE, extraTitle);
        context.startActivity(intent);
    }

    private void getIntentData() {
        URL = getIntent().getStringExtra(GANK_URL);
        TITLE = getIntent().getStringExtra(GANK_TITLE);
    }

    @Override
    protected int initView() {
        return R.layout.activity_gank_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getIntentData();

        setTitle(TITLE);
        setSupportActionBar(mToolbar);
        //actionbar显示返回按钮
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //取消阴影
            getSupportActionBar().setElevation(0);
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            mToolbar.setNavigationOnClickListener(v -> finish());
        }
        initWebViewSettings();
    }


    /**
     * 初始化WebView
     */
    private void initWebViewSettings() {
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
        if (!NetWorkUtil.isNetWorkAvailable(this))
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        else
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setBuiltInZoomControls(true); // 支持手势缩放
        webSettings.setDisplayZoomControls(false); // 不显示缩放按钮

        webSettings.setDatabaseEnabled(true);
        webSettings.setSaveFormData(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setUseWideViewPort(true); // 将图片调整到适合WebView的大小
        webSettings.setLoadWithOverviewMode(true); // 自适应屏幕

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null)
                    view.loadUrl(url);
                return true;
            }
        });

        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.loadUrl(URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                mWebView.reload();
                break;
            case R.id.action_copy:
                copyUrlToClipBard();
                break;
            case R.id.action_share:
                shareUrl();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void copyUrlToClipBard() {
        ClipboardManager clipboardManager = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("URL", URL);
        clipboardManager.setPrimaryClip(clipData);
        ToastUtils.showToast(this, "链接已复制到粘贴板");
    }

    private void shareUrl() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, URL);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "分享到..."));
    }


    @Override
    public void onPause() {
        if (mWebView != null)
            mWebView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        if (mWebView != null)
            mWebView.onResume();
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
    public void onDestroy() {
        //  在 Activity 销毁（ WebView ）的时候，先让 WebView 加载null内容，然后移除 WebView，再销毁 WebView，最后置空
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    /**
     * WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
     */
    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (mProgressBar != null) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() == View.GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
            }
            super.onProgressChanged(view, newProgress);
        }

        //获取Web页中的title用来设置自己界面中的title
        //当加载出错的时候，比如无网络，这时onReceiveTitle中获取的标题为 找不到该网页,
        //因此建议当触发onReceiveError时，不要使用获取到的title
        @Override
        public void onReceivedTitle(WebView view, String title) {
            setTitle(title);
        }
    }

}
