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
public class BlogsService {

    private Map<Long, Blog> blogs;
    private long blogId;

    @PostConstruct
    public void init() {
        blogs = new HashMap<Long, Blog>();

        put(new Blog("Семья", 1, "У Меня большая семья", 1));
        put(new Blog("Отдых", 2, "Я люблю отдыхать на природе", 2));
        put(new Blog("Работа", 1, "У меня интересная работа", 3));
        put(new Blog("Лошадка", 2, "Мою лошадку зовут Эбигейла, ей 6 лет", 4));
    }

    private void put(Blog blog) {
        blogId++;
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

    public void addBlogText(long blogid, long userId, String blogText) {
        if (blogText == null) {
            throw new IllegalArgumentException("Empty text");
        }
        Blog blog = getBlogById(blogid);
        if (blog.getUserId() == userId) {
            blog.setText(blogText);
        } else {
            throw new IllegalArgumentException("Wrong user id");
        }
    }

}
