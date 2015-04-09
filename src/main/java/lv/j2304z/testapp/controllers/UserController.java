package lv.j2304z.testapp.controllers;

import lv.j2304z.testapp.domain.Blog;
import lv.j2304z.testapp.domain.User;
import lv.j2304z.testapp.dto.UserDTO;
import lv.j2304z.testapp.dto.UsersResponse;
import lv.j2304z.testapp.services.BlogService;
import lv.j2304z.testapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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

        @RequestMapping(method = RequestMethod.GET, value = "/usersJson")
        public @ResponseBody
        UsersResponse getUsers(Model model) {
            List<User> users = userService.getAllUsers();
            return convertToDto(users);
        }
         private UsersResponse convertToDto(List<User> users){
             UsersResponse result = new UsersResponse();
             result.setUsers(convertUsers(users));
             return result;
         }

    private List<UserDTO> convertUsers(List<User> users) {
        List<UserDTO> result = new ArrayList<UserDTO>();
        for (User user : users) {
            result.add(convertToUser(user));
        }
        return result;
    }

    private UserDTO convertToUser(User user) {
        UserDTO result = new UserDTO();
        result.setName(user.getName());
        return result;
    }

    @RequestMapping(value = "/usersJs", method = RequestMethod.GET)
        public String getUserFromJson() {
            return "usersJs";
        }
}
