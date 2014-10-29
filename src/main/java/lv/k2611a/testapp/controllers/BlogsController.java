package lv.k2611a.testapp.controllers;

import lv.k2611a.testapp.domain.Blog;
import lv.k2611a.testapp.domain.User;
import lv.k2611a.testapp.services.BlogsService;
import lv.k2611a.testapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * контроллер блога
 */
@Controller
public class BlogsController {


    @Autowired
    private BlogsService blogsService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userId}/blog/{blogId}")
    public String getBlogPage(Model model, @PathVariable Long userId, @PathVariable long blogId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return "404";
        }
        model.addAttribute("user", user);

        Blog blog = blogsService.getBlogById(blogId);
        if (blog == null) {
            return "404";
        }
        model.addAttribute("blog",blog);
        return "blog";

    }

    @RequestMapping(value = "/user/{userId}/blog/{blogId}", method = RequestMethod.POST)
    public String addBlogText(Model model,
                              @PathVariable long userId,
                              @PathVariable long blogId,
                              @RequestParam("blogText") String blogText) {
        blogsService.addBlogText(blogId, userId, blogText);

        return getBlogPage(model, userId, blogId);
    }
}