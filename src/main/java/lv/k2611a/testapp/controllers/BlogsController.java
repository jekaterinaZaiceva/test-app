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
    public String getMainPage(Model model, @PathVariable Long userId, @PathVariable Integer blogId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return "404";
        }
        model.addAttribute("user",user.getName());

        Blog blog = blogsService.getBlogById(blogId);
        if (blog == null) {
           return "404";
        }
        model.addAttribute("blog", blog.getText());
        model.addAttribute("userId", blog.getUserId());
        return "blog";
    }
}