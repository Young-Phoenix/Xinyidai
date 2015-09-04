package cn.net.xyd.entity;

/**
 * Created by Administrator on 2015/9/4 0004.
 */
public class Message {
    private int id;
    private String title;
    private String time;
    public Message(){}
    public Message(String title, String time) {
        this.title = title;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }
}
