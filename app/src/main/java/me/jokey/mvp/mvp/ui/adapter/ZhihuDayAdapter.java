package me.jokey.mvp.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.jokey.mvp.R;
import me.jokey.mvp.mvp.ui.entity.zhihu.StoriesBean;

/**
 * Created by wz on 2017/6/20 18:22.
 * ZhihuDayAdapter
 */

public class ZhihuDayAdapter extends BaseQuickAdapter<StoriesBean,BaseViewHolder>{

    public ZhihuDayAdapter() {
        super(R.layout.item_recycle_zhihu_day);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoriesBean item) {
        helper.setText(R.id.tv_zhihu_title, item.getTitle());

        ImageView imageView = helper.getView(R.id.iv_zhihu_img);
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
