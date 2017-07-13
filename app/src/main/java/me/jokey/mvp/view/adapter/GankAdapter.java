package me.jokey.mvp.view.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.jokey.mvp.R;
import me.jokey.mvp.application.BaseApplication;
import me.jokey.mvp.model.entity.gank.ResultBean;
import me.jokey.mvp.utils.ScreenUtils;

/**
 * Created by wz on 2017/6/22 14:11.
 * GankAdapter 适配器
 */

public class GankAdapter extends BaseQuickAdapter<ResultBean, BaseViewHolder> {

    private int type;
    private SparseArray<Integer> heightArray;
    private int mWidth;

    public GankAdapter(@LayoutRes int layoutResId, int type) {
        super(layoutResId);
        this.type = type;
        if (type == 0) {
            heightArray = new SparseArray<>();
            mWidth = ScreenUtils.getScreenWidth(BaseApplication.getContext()) / 2;
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultBean item) {
        if (type == 0) {
            final int position = helper.getLayoutPosition();
            final ImageView photoIv = helper.getView(R.id.iv_girl_img);
            if (heightArray.get(position) == null) {
                Glide.with(this.mContext)
                        .load(item.getUrl())
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                int originalHeight = resource.getHeight();
                                int originalWidth = resource.getWidth();
                                FrameLayout.LayoutParams layoutParams =
                                        (FrameLayout.LayoutParams) photoIv.getLayoutParams();
                                int height = resizeHeight(originalHeight, originalWidth);
                                layoutParams.height = height;
                                photoIv.setLayoutParams(layoutParams);
                                heightArray.put(position, height);
                            }

                            private int resizeHeight(int originalHeight, int originalWidth) {
                                int scale = originalWidth / mWidth;
                                return scale <= 0 ? 1 : originalHeight / scale;
                            }
                        });
            } else {
                int height = heightArray.get(position);
                FrameLayout.LayoutParams layoutParams =
                        (FrameLayout.LayoutParams) photoIv.getLayoutParams();
                layoutParams.height = height;
                photoIv.setLayoutParams(layoutParams);
            }

            Glide.with(mContext)
                    .load(item.getUrl())
                    .fitCenter()
                    .into(photoIv);
        } else {
            StringBuffer sb = new StringBuffer(" on ");
            sb.append(item.getPublishedAt().substring(0,10));
            sb.append(item.getPublishedAt().substring(12,19));
            helper.setText(R.id.tv_gank_title, item.getDesc())
                    .setText(R.id.tv_gank_author, "Created by "+item.getWho())
                    .setText(R.id.tv_gank_date, sb.toString());

            ImageView imageView = helper.getView(R.id.iv_gank_img);
            if (item.getImages() != null && item.getImages().size() > 0) {
                imageView.setVisibility(View.VISIBLE);

                Glide.with(this.mContext)
                        .load(item.getImages().get(0))
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .crossFade()
                        .into(imageView);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
    }
}
