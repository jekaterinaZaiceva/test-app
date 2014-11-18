package lv.k2611a.testapp.controllers;

import lv.k2611a.testapp.domain.LoginInfo;
import lv.k2611a.testapp.domain.User;
import lv.k2611a.testapp.services.UserService;
import lv.k2611a.testapp.sessions.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String login(@ModelAttribute("loginInfo") LoginInfo loginInfo) {
        Boolean isValid = userService.authenticateUser(loginInfo.getUsername(), loginInfo.getPassword());

        if (isValid) {
            User user = userService.getUserByName(loginInfo.getUsername());
            currentUser.setLogin(loginInfo.getUsername());
            currentUser.setId(user.getId());
            return "redirect:user/"+user.getId();
        }
        return "404";
    }
}
