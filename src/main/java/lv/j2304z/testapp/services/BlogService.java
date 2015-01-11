package lv.j2304z.testapp.services;

import lv.j2304z.testapp.domain.Blog;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.*;

/**
 * сервис блоков
 */
@Service
public class BlogService implements Serializable {

    public static final String DATA_DB = "data.db";
    private Map<Long, Blog> blogs;
    private long blogId;

    @PostConstruct
   // public void init() {
   // blogs = new HashMap<Long, Blog>();

    // put(new Blog("Семья", 0, "У Меня большая семья"));
    // put(new Blog("Отдых", 1, "Я люблю отдыхать на природе"));
    // put(new Blog("Работа", 0, "У меня интересная работа"));
    // put(new Blog("Лошадка", 1, "Мою лошадку зовут Эбигейла, ей 6 лет"));
   // }

     //   try {
       //     this.save();
       // } catch (IOException e) {
       //     e.printStackTrace();
       // }

        public void init() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(new File(DATA_DB));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            blogs = (HashMap<Long, Blog>) ois.readObject();
            blogId = (Long)ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void save() throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(new File(DATA_DB));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(blogs);
            oos.writeObject((Long) blogId);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        allByUser.clear();
        save();
    }
}
