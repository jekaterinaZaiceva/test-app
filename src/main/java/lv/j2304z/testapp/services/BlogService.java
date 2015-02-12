package lv.j2304z.testapp.services;

import lv.j2304z.testapp.domain.Blog;
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

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);
    public static final String DATA_DB = "data.db";
    private Map<Long, Blog> blogs;
    private long blogId;

    @PostConstruct
    public void init() {

        try {
            FileInputStream fis = new FileInputStream(new File(DATA_DB));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            blogs = (HashMap<Long, Blog>) ois.readObject();
            blogId = (Long)ois.readObject();
            ois.close();
        } catch (IOException e) {
            logger.error("Произошла ошибка при сохранении действий", e);
        } catch (ClassNotFoundException e) {
            logger.error("Произошла ошибка, класс не найден", e);
        }
    }

    @PreDestroy
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(new File(DATA_DB));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(blogs);
            oos.writeObject((long) blogId);
            oos.close();
        } catch (IOException e) {
            logger.error("Произошла ошибка при сохранении действий",e);
        }
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

    public void addBlog(String blogName, long userId) throws IOException{
        if (blogName == null) {
            throw new IllegalArgumentException("Empty text");
        }

        Blog blog = new Blog(blogName, userId);
        put(blog);
        save();

    }

    public void addBlogText(long blogId, long userId, String blogText) throws IOException {
        if (blogText == null) {
            throw new IllegalArgumentException("Empty text");
        }
        Blog blog = getBlogById(blogId);
        if (blog.getUserId() == userId) {
            blog.setText(blogText);
        } else {
            throw new IllegalArgumentException("Wrong user id");
        }
        save();
    }

    public void deleteUserBlogs(long userId) throws IOException {

        List<Blog> allByUser = getAllByUser(userId);

        for (Blog blog : allByUser) {
            blogs.remove(blog.getBlogId());
        }
        save();
    }

    public void editBlog(String blogName, long blogId, long userId) {
        if (blogName == null) {
            throw new IllegalArgumentException("Empty text");
        }
        Blog blog = getBlogById(blogId);
        if (blog.getUserId() == userId) {
            blog.setName(blogName);
            save();
        } else {
            throw new IllegalArgumentException("Wrong user id");
        }

    }
}
