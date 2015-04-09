package lv.j2304z.testapp.domain;

/**
 * Created by jekaterina.zaiceva on 09.04.15.
 */
public class BlogText {

            private long blogId;
            private String text;

    public BlogText(){

    }
    public BlogText(long blogId, String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.blogId = blogId;
        this.text = text;
    }

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
