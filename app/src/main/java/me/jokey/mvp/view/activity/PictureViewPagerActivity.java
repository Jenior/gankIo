package me.jokey.mvp.view.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.jokey.mvp.R;
import me.jokey.mvp.contract.DownImageContract;
import me.jokey.mvp.model.DownImageModel;
import me.jokey.mvp.presenter.DownImagePresenter;
import me.jokey.mvp.model.entity.gank.ResultBean;
import me.jokey.mvp.view.widget.HackyViewPager;
import me.jokey.mvp.utils.LogUtils;
import me.jokey.mvp.utils.PermissionListener;
import me.jokey.mvp.utils.ToastUtils;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by wz on 2017/6/22 17:08.
 * PictureViewPagerActivity.java
 */

public class PictureViewPagerActivity extends BaseFrameActivity<DownImagePresenter, DownImageModel>
        implements ViewPager.OnPageChangeListener, DownImageContract.View {

    private static final String EXTRA_POSITION = "extraPosition";
    private static final String EXTRA_ARRAY_LIST_URL = "ARRAY_LIST_URL";

    @BindView(R.id.view_pager)
    HackyViewPager mViewPager;
    @BindView(R.id.tv_page_num)
    TextView mPageNum;

    private ProgressDialog mProgressDialog;
    private ArrayList<ResultBean> mList;

    public static void start(Context context, int extraPosition, ArrayList<ResultBean> extraList) {
        Intent intent = new Intent(context, PictureViewPagerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_POSITION, extraPosition);
        bundle.putSerializable(EXTRA_ARRAY_LIST_URL, extraList);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int initView() {
        return R.layout.activity_picture_viewpager;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 申请权限
                int checkSelfPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionListener() {
                        @Override
                        public void onGranted() {

                        }

                        @Override
                        public void onDenied(List<String> deniedPermissions) {

                        }
                    });
                }
            }
        }

        int extraPosition = getIntent().getExtras().getInt(EXTRA_POSITION);
        mList = (ArrayList<ResultBean>) getIntent().getExtras().getSerializable(EXTRA_ARRAY_LIST_URL);
        mViewPager.setAdapter(new SamplePagerAdapter(mList));
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(extraPosition);
        mPageNum.setText(extraPosition + 1 + " / " + mList.size());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mPageNum.setText(position + 1 + " / " + mList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void showShare(String id, final String url) {
        String sdCardPath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(sdCardPath + "/GankShareImg");
        String filePath = (file.exists() ? file.isDirectory() : file.mkdirs()) ? file.toString() : sdCardPath;

        final String fileName = id + ".jpg";
        File imgFile = new File(filePath, fileName);

        if (imgFile.exists()) {
            LogUtils.debugInfo(imgFile.toString());
            startShare(imgFile);
        } else mPresenter.getGankImage(imgFile, url);
    }

    public void startShare(File file) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (file.exists() && file.isFile()) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/*");
            LogUtils.debugInfo(file.toString());
        }
        startActivity(Intent.createChooser(intent, "分享到..."));
    }

    @Override
    public void onRequestStart() {
        super.onRequestStart();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMessage("图片下载中，请稍候...");
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.show();
    }

    @Override
    public void onRequestEnd() {
        super.onRequestEnd();
    }

    @Override
    public void loadFile(File file) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        startShare(file);
    }

    @Override
    public void onRequestError(String msg) {
        super.onRequestError(msg);
        LogUtils.debugInfo("下载图片失败，" + msg);
        ToastUtils.showToast(this, "下载图片失败，" + msg);
    }

    private class SamplePagerAdapter extends PagerAdapter {

        private List<ResultBean> mList;

        SamplePagerAdapter(List<ResultBean> list) {
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            final PhotoView photoView = new PhotoView(container.getContext());

            Glide.with(container.getContext())
                    .load(mList.get(position).getUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade()
                    .into(photoView);

            photoView.setOnLongClickListener(v -> {
                showShare(mList.get(position).get_id(), mList.get(position).getUrl());
                return false;
            });

            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
