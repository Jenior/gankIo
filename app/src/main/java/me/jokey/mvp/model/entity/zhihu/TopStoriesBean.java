package me.jokey.mvp.model.entity.zhihu;

/**
 * Created by wz on 2017/6/20 15:38.
 * TopStoriesBean
 */


public class TopStoriesBean {

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "TopStoriesBean{" +
                "image='" + image + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
