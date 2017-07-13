package me.jokey.mvp.mvp.ui.entity.zhihu;

import java.util.List;

/**
 * Created by wz on 2017/6/20 15:37.
 * StoriesBean
 */


public class StoriesBean {

    private String title;
    private String ga_prefix;
    private boolean multipic;
    private int type;
    private int id;
    private List<String> images;

    public String getTitle() {
        return title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public List<String> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "StoriesBean{" +
                "title='" + title + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", multipic=" + multipic +
                ", type=" + type +
                ", id=" + id +
                ", images=" + images +
                '}';
    }
}
