package lv.j2304z.testapp.controllers;

import lv.j2304z.testapp.domain.Blog;
import lv.j2304z.testapp.domain.User;
import lv.j2304z.testapp.services.BlogService;
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
            @RequestParam("blogName") String blogName) throws IOException {
        blogService.addBlog(blogName, userId);

        return getUserPage(model, userId);
    }

    @RequestMapping(value = "/user/{userId}/edit/{blogId}", method = RequestMethod.POST)
    public String editBlog(
            Model model,
            @PathVariable long userId,
            @PathVariable long blogId,
            @RequestParam("blogName") String blogName) throws IOException {
            blogService.editBlogName(blogName, blogId, userId);

        return getUserPage(model, userId);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getallUsersPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "allUser";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String deleteUser(
            Model model,
            @RequestParam("userId") long userId) throws IOException {
        userService.deleteUser(userId);
        return getallUsersPage(model);
    }

}
