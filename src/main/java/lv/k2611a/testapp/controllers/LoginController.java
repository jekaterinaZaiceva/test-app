package lv.k2611a.testapp.controllers;

import lv.k2611a.testapp.domain.LoginInfo;
import lv.k2611a.testapp.domain.User;
import lv.k2611a.testapp.services.UserService;
import lv.k2611a.testapp.sessions.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HttpSessionMutexListener;
import javax.servlet.http.HttpSession;
/**
 * Created by jekaterina.zaiceva on 17.11.2014.
 */

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private CurrentUser currentUser;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String password, Model model) {
        try {
            Boolean isValid = userService.authenticateUser(userName, password);

            if (isValid) {

                User user = userService.getUserById(userService.getUserByName(userName));
                currentUser.setLogin(userName);
                currentUser.setId(user.getId());
                return "redirect:user/" + user.getId();
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "error");
            return "index";
        }
        return "404";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:";
    }
}
