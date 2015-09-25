package cn.net.xyd.entity;

/**
 * @FileName:cn.net.xyd.entity.ListMenu.java
 * @author:Phoenix
 * @date:2015-09-23 09:30
 * @Version:V1.0
 */
public class ListMenu {
    private static final String TAG = "ListMenu";
    private String picUrl;
    private String title;
    private String content;

    public ListMenu(String picUrl, String title, String content) {
        this.picUrl = picUrl;
        this.title = title;
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
