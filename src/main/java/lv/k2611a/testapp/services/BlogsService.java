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

    private Map<Integer,Blog> blogs;

    @PostConstruct
    public void init() {
        blogs = new HashMap<Integer, Blog>();

        put(new Blog("Семья", 1,"У Меня большая семья",1));
        put(new Blog("Отдых", 2, "Я люблю отдыхать на природе",2));
        put(new Blog("Работа", 1,"У меня интересная работа",3));
        put(new Blog("Лошадка",2, "Мою лошадку зовут Эбигейла, ей 6 лет",4));
    }
    private void put(Blog blog){
        blogs.put(blog.getBlogId(),blog);
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

    public Blog getBlogById(Integer blogIndex){
        if(blogs.containsKey(blogIndex)){
            return blogs.get(blogIndex);
        }
        return null;
    }

}
