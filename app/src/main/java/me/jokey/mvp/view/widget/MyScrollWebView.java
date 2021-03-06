package me.jokey.mvp.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by wz on 2017/6/22 16:21.
 * MyScrollWebView.java
 */

public class MyScrollWebView extends WebView {

    private MyScrollListener scrollListener;


    public MyScrollWebView(Context context) {
        super(context);
    }

    public MyScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollListener(MyScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int dy = t - oldt;
        if (scrollListener != null)
            scrollListener.onScroll(dy);
    }

    public interface MyScrollListener {
        void onScroll(int dy);
    }

}
