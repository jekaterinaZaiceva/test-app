package lv.k2611a.testapp.services;

import lv.k2611a.testapp.domain.Blog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by jekaterina.zaiceva on 08.10.2014.
 */
@Service
public class BlogsService {

    private Map<Integer,Blog> blogs;

    @PostConstruct
    public void init() {
        blogs = new HashMap<Integer, Blog>();
        blogs.put(1, new Blog("family", 1,"tekstik u bloga 1",1));
        blogs.put(2, new Blog("rest", 2, "tekstik u bloga 2",3));
        blogs.put(3, new Blog("my private", 1,"tekstik u bloga 3",3));
        blogs.put(4, new Blog("horse",2, "tekstik u bloga 4",4));
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
