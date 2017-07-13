package me.jokey.mvp.model.entity.zhihu;

import java.util.List;

/**
 * Created by wz on 2017/6/22 11:58.
 * ZhihuThemeDetail
 */


public class ZhihuThemeDetail {

    /**
     * description : 除了经典和新片，我们还关注技术和产业
     * background : http://p1.zhimg.com/80/0b/800b79a4821a535de31b349ffdc9eabb.jpg
     * color : 14483535
     * name : 电影日报
     * image : https://pic1.zhimg.com/ddf10a04227ea50fd59746dbcd13c728.jpg
     * image_source :
     */

    private String description;
    private String background;
    private int color;
    private String name;
    private String image;
    private String image_source;
    private List<StoriesBean> stories;
    private List<EditorBean> editors;

    public String getDescription() {
        return description;
    }

    public String getBackground() {
        return background;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getImage_source() {
        return image_source;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public List<EditorBean> getEditors() {
        return editors;
    }
}
