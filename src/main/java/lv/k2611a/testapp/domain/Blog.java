package lv.k2611a.testapp.domain;

/**
 * Cдомен блог
 */
public class Blog {
    private String name;
    private long userId;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    private Integer blogId;

    public String getText() {
        return text;
    }

    private String text;
    public Blog(String name, long userId, String text, Integer blogId) {
        this.name = name;
        this.userId = userId;
        this.text = text;
        this.blogId = blogId;
    }

    public String getName() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
