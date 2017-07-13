package me.jokey.mvp.mvp.ui.entity.zhihu;

import java.util.List;

/**
 * Created by wz on 2017/6/22 11:17.
 * ZhihuTheme
 */


public class ZhihuTheme {

    /**
     * limit : 1000
     * subscribed : []
     * others : []
     */

    private int limit;
    private List<String> subscribed;
    private List<OtherBean> others;

    public int getLimit() {
        return limit;
    }

    public List<String> getSubscribed() {
        return subscribed;
    }

    public List<OtherBean> getOthers() {
        return others;
    }
}
