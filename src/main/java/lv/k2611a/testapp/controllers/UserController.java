package lv.k2611a.testapp.controllers;

import lv.k2611a.testapp.domain.Blog;
import lv.k2611a.testapp.domain.User;
import lv.k2611a.testapp.services.BlogService;
import lv.k2611a.testapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 * контроллер пользователя
 */
@Controller
public class UserController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String getUserPage(Model model, @PathVariable Long userId) {

        User user = userService.getUserById(userId);
        if (user == null) {
            return "404";
        }
        List<Blog> blogs = blogService.getAllByUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("blogs", blogs);

        return "user";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
    public String addBlog(
            Model model,
            @PathVariable long userId,
            @RequestParam("blogName") String blogName) {
        blogService.addBlog(blogName, userId);

        return getUserPage(model, userId);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getallUsersPage(Model model) {
        Map<Long, User> users = userService.getAll();
        model.addAttribute("users", users);

        return "allUser";
    }
    @RequestMapping(value = "/users", method  = RequestMethod.POST)
    public String deleteUser(
            Model model,
            @RequestParam("userId") Long userId){
        userService.deleteUser(userId);
        blogService.deleteUserBlogs(userId);
        return getallUsersPage(model);
    }



}
