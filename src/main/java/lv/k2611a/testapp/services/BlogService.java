package lv.k2611a.testapp.services;

import lv.k2611a.testapp.domain.Blog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * сервис блоков
 */
@Service
public class BlogService {

    private Map<Long, Blog> blogs;
    private long blogId;

    @PostConstruct
    public void init() {
        blogs = new HashMap<Long, Blog>();

        put(new Blog("Семья", 1, "У Меня большая семья"));
        put(new Blog("Отдых", 2, "Я люблю отдыхать на природе"));
        put(new Blog("Работа", 1, "У меня интересная работа"));
        put(new Blog("Лошадка", 2, "Мою лошадку зовут Эбигейла, ей 6 лет"));
    }

    private void put(Blog blog) {
        blogId++;
        blog.setBlogId(blogId);
        blogs.put(blog.getBlogId(), blog);
    }

    public List<Blog> getAllByUser(long userId) {
        List<Blog> userBlogs = new ArrayList<Blog>();
        for (Map.Entry entry : blogs.entrySet()) {
            Blog value = (Blog) entry.getValue();
            if (value.getUserId() == userId) {
                userBlogs.add(value);
            }
        }
        return userBlogs;
    }


    public Blog getBlogById(long blogIndex) {
        return blogs.get(blogIndex);
    }

    public void addBlog(String blogName, long userId) {
        if (blogName == null) {
            throw new IllegalArgumentException("Empty text");
        }

        Blog blog = new Blog(blogName, userId);
        put(blog);
    }

    public void addBlogText(long blogId, long userId, String blogText) {
        if (blogText == null) {
            throw new IllegalArgumentException("Empty text");
        }
        Blog blog = getBlogById(blogId);
        if (blog.getUserId() == userId) {
            blog.setText(blogText);
        } else {
            throw new IllegalArgumentException("Wrong user id");
        }
    }

    public void deleteUserBlogs(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Empty user");
        }
        List<Blog> allByUser = getAllByUser(userId);
        if (allByUser.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userId + " doesn't has blogs");
        }
        allByUser.clear();

    }

}
