package lv.j2304z.testapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Blog implements Serializable {
    static final long serialVersionUID = -2077650952021570622L;

    private String name;
    private long userId;
    private long blogId;
    private String text;

    public Blog(String name, long userId) {
        this.name = name;
        this.userId = userId;
    }

    public Blog(String name, long userId, String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.name = name;
        this.userId = userId;
        this.text = text;
    }
    public Blog(){

    }

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }
    public void setText(String text){
        this.text = text;
    }
}
