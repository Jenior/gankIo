package me.jokey.mvp.model.entity.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wz on 2017/6/22 14:13.
 * ResultBean
 */


public class ResultBean implements Serializable{

    /**
     * _id : 59394867421aa92c79463387
     * createdAt : 2017-06-08T20:51:51.475Z
     * desc : 简单高效的实现Android App全局字体替换
     * images : ["http://img.gank.io/116bb496-79cb-4e31-8823-4389bfa6b629"]
     * publishedAt : 2017-06-09T12:50:03.131Z
     * source : web
     * type : Android
     * url : http://www.jianshu.com/p/4e1e96fe6d26
     * used : true
     * who : 黎赵太郎
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isUsed() {
        return used;
    }

    public String getWho() {
        return who;
    }

    public List<String> getImages() {
        return images;
    }
}
