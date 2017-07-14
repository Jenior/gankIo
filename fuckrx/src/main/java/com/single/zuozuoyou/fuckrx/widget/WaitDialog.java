package com.single.zuozuoyou.fuckrx.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.single.zuozuoyou.fuckrx.R;


/**
 * 不能关闭的等待dialog
 *
 * @author zuozuoyou
 * @date 2016/1/29
 */
public class WaitDialog extends Dialog implements DialogInterface.OnKeyListener, DialogInterface.OnShowListener {

    private Activity activity;

    private boolean closeable = true;

    private TextView msgTv;

    private String mMsg = "努力加载中";


    public WaitDialog(Context context, int themeResId, Activity activity) {
        super(context, themeResId);
        this.activity = activity;
    }

    public WaitDialog(Context context, boolean cancelable, OnCancelListener cancelListener, Activity activity) {
        super(context, cancelable, cancelListener);
        this.activity = activity;
    }

    public WaitDialog(Activity activity) {
        super(activity, R.style.dialog_transform);
        this.activity = activity;
    }

    public WaitDialog(Context context) {
        super(context, R.style.dialog_transform);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
        setOnKeyListener(this);
        setContentView(R.layout.dialog_waiting);
        msgTv = (TextView) findViewById(R.id.dialog_waiting_msg);
        msgTv.setText(mMsg);
        this.setOnShowListener(this);
    }

    public void setMessage(String message) {
        this.mMsg = message;
    }

    public void closeable() {
        closeable(true);
    }

    public void closeable(boolean closeable) {
        this.closeable = closeable;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (!closeable)
            return false;

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            WaitDialog.this.cancel();
//            activity.finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
    };
}
