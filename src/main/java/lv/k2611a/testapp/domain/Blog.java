package lv.k2611a.testapp.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Cдомен блог
 */
public class Blog {
    private String name;
    private long userId;
    private long blogId;
    private List<String> textList = new ArrayList<String>();

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }


    public List getTextList() {
        return textList;
    }

    public Blog(String name, long userId) {
        this.name = name;
        this.userId = userId;
        //this.blogId = blogId;
    }

    public Blog(String name, long userId, String text, long blogId) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.name = name;
        this.userId = userId;
        this.blogId = blogId;
        this.textList.add(text);
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
