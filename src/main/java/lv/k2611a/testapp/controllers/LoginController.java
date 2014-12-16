package lv.k2611a.testapp.controllers;

import lv.k2611a.testapp.domain.LoginInfo;
import lv.k2611a.testapp.domain.User;
import lv.k2611a.testapp.services.UserService;
import lv.k2611a.testapp.services.exceptions.IncorrectPasswordException;
import lv.k2611a.testapp.services.exceptions.UserNotFoundException;
import lv.k2611a.testapp.sessions.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
                 userService.authenticateUser(userName, password);
                 User user = userService.getUserByName(userName);
                currentUser.setLogin(userName);
                currentUser.setId(user.getId());
            return "redirect:/user/"+user.getId();

        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Юзер не найден");
            return "index";
        } catch (IncorrectPasswordException e){
            model.addAttribute("error","Неправильный пароль");
            return "index";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:";
    }
}
