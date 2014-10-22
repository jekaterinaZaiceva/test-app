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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * контроллер пользователя
 */
@Controller
public class UserController {

    @Autowired
    private BlogsService blogsService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userId}")
    public String getMainPage(Model model, @PathVariable Long userId) {
        List<Blog> blogs = blogsService.getAllByUser(userId);
        if (blogs == null) {
            return "404";
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            return "404";
        }
        model.addAttribute("user",user.getName());
        model.addAttribute("blogs",blogs);

        return "user";
    }
}
