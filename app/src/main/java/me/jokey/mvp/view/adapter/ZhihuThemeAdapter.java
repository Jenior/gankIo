package me.jokey.mvp.view.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.jokey.mvp.R;
import me.jokey.mvp.model.entity.zhihu.OtherBean;

/**
 * Created by wz on 2017/6/16 16:51.
 * ZhihuAdapter
 */

public class ZhihuThemeAdapter extends BaseQuickAdapter<OtherBean, BaseViewHolder> {

    public ZhihuThemeAdapter() {
        super(R.layout.item_recycle_zhihu_theme);
    }

    @Override
    protected void convert(BaseViewHolder helper, OtherBean item) {
        helper.setText(R.id.tv_zhihu_theme_name, item.getName())
                .setText(R.id.tv_zhihu_theme_description, item.getDescription());

        ImageView imageView = helper.getView(R.id.iv_zhihu_theme_img);
        Glide.with(this.mContext)
                .load(item.getThumbnail())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(imageView);
    }
}
