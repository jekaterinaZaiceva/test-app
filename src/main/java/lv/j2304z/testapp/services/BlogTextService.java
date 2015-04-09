package lv.j2304z.testapp.services;

import lv.j2304z.testapp.dao.BlogDao;
import lv.j2304z.testapp.dao.BlogTextDao;
import lv.j2304z.testapp.domain.Blog;
import lv.j2304z.testapp.domain.BlogText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Created by jekaterina.zaiceva on 09.04.15.
 */

@Service
public class BlogTextService {

    @Autowired
    BlogTextDao blogTextDao;
    @Autowired
    BlogDao blogDao;

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);

    @PostConstruct
    public void init() {
    }


    private void put(long blogId, String text) {
        BlogText blogText = new BlogText(blogId,text);
        blogTextDao.addBlogText(blogText);
    }

    public void addBlogText(long blogId, long userId, String blogText) throws IOException {
        if (blogText == null) {
            throw new IllegalArgumentException("Empty text");
        }


        Blog blog = blogDao.getBlogById(blogId);
        //BlogText blogText = blogTextDao.getBlogTextByBlogId(blogId);

        //blog.setText(blogText);

        if (blog.getUserId() == userId) {
            put(blogId,blogText);
            //blogDao.addBlogText(blog);
        } else {
            throw new IllegalArgumentException("Wrong user id");
        }
    }

    public List<BlogText> getBlogTextByBlogId(long blogId) {

        return blogTextDao.getBlogTextByBlogId(blogId);
    }
}
