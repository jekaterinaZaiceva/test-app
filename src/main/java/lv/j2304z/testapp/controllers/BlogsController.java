package lv.j2304z.testapp.controllers;

import lv.j2304z.testapp.domain.Blog;
import lv.j2304z.testapp.domain.BlogText;
import lv.j2304z.testapp.domain.User;
import lv.j2304z.testapp.services.BlogService;
import lv.j2304z.testapp.services.BlogTextService;
import lv.j2304z.testapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


/**
 * контроллер блога
 */
@Controller
public class BlogsController {


    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTextService blogTextService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userId}/blog/{blogId}")
    public String getBlogPage(Model model, @PathVariable Long userId, @PathVariable long blogId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return "404";
        }
        model.addAttribute("user", user);

        Blog blog = blogService.getBlogById(blogId);
        model.addAttribute("blog", blog);

        List<BlogText> blogText = blogTextService.getBlogTextByBlogId(blogId);
        model.addAttribute("blogText", blogText);
        return "blog";
    }

    @RequestMapping(value = "/user/{userId}/blog/{blogId}", method = RequestMethod.POST)
    public String addBlogText(Model model,
                              @PathVariable long userId,
                              @PathVariable long blogId,
                              @RequestParam("blogText") String blogText) throws IOException {
        blogTextService.addBlogText(blogId, userId, blogText);

        return getBlogPage(model, userId, blogId);
    }
}