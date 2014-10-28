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
    private List<String> text = new ArrayList<String>();

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }


    public List getText() {
        return text;
    }

    public Blog(String name, long userId, String text, long blogId) {
        this.name = name;
        this.userId = userId;
        this.text.add(text);
        this.blogId = blogId;
    }

    public String getName() {
        return name;
    }

    public void setText(String text) {
        this.text.add(text);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
