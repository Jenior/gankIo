package me.jokey.mvp.model.entity.zhihu;

import java.util.List;

/**
 * Created by wz on 2017/6/20 15:35.
 * ZhihuDay
 */


public class ZhihuDay {

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    @Override
    public String toString() {
        return "ZhihuDay{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
