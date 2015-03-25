package lv.j2304z.testapp.services;

import lv.j2304z.testapp.dao.BlogDao;
import lv.j2304z.testapp.domain.Blog;
import lv.j2304z.testapp.sessions.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * сервис блоков
 */
@Service
public class BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    CurrentUser currentUser;

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);

    @PostConstruct
    public void init() {
    }


    private void put(String name, long userId ) {
        Blog blog = new Blog(name,userId);
        blogDao.addBlog(blog);
    }

    public List<Blog> getAllByUser(long userId) {

        return blogDao.getAllByUser(userId);
    }


    public Blog getBlogById(long blogId) {
        return blogDao.getBlogById(blogId);
    }

    public void addBlog(String blogName, long userId) throws IOException {
        if (blogName == null) {
            throw new IllegalArgumentException("Empty text");
        }
        put(blogName,userId);

    }

    public void addBlogText(long blogId, long userId, String blogText) throws IOException {
        if (blogText == null) {
            throw new IllegalArgumentException("Empty text");
        }
        Blog blog = blogDao.getBlogById(blogId);

        if (blog.getUserId() == userId) {
            blogDao.addBlogText(blogText,blogId);
        } else {
            throw new IllegalArgumentException("Wrong user id");
        }
    }

    public void editBlogName(String blogName, long blogId, long userId) {
        if (currentUser.getId() == userId) {
            if (blogName == null) {
                throw new IllegalArgumentException("Empty text");
            }
            Blog blog = blogDao.getBlogById(blogId);
            if (blog.getUserId() == userId) {
                blogDao.editBlogName(blogName,blogId);

            } else {
                throw new IllegalArgumentException("Wrong user id");
            }

        } else {
            throw new IllegalArgumentException("can't edit blog");
        }
    }

    public void deleteUserBlogs(long userId) {
        blogDao.deleteUserBlogs(userId);
    }
}
