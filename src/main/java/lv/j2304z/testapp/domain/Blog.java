package lv.j2304z.testapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Blog implements Serializable {
    static final long serialVersionUID = -2077650952021570622L;

    private String name;
    private long userId;
    private long blogId;
    private List<String> textList = new ArrayList<String>();

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
        this.textList.add(text);
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

    public List getTextList() {
        return textList;
    }

    public String getName() {
        return name;
    }

    public void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.textList.add(text);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
