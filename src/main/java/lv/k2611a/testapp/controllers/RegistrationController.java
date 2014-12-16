package lv.k2611a.testapp.controllers;

import lv.k2611a.testapp.domain.User;
import lv.k2611a.testapp.services.UserService;
import lv.k2611a.testapp.services.exceptions.DublicatedSymbolException;
import lv.k2611a.testapp.services.exceptions.SmallPasswodsException;
import lv.k2611a.testapp.services.exceptions.UserAlreadyExistException;
import lv.k2611a.testapp.sessions.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by jekaterina.zaiceva on 02.12.2014.
 */

@Controller
public class RegistrationController{
    @Autowired
    private UserService userService;
    @Autowired
    private CurrentUser currentUser;

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String regists(){
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(String userName, String password, Model model) {

        try {

            userService.registerUser(userName, password);

            User user = userService.getUserByName(userName);
            currentUser.setLogin(userName);
            currentUser.setId(user.getId());
            return "redirect:user/" + user.getId();

        } catch (UserAlreadyExistException e) {
            model.addAttribute("error", "Такой логин уже существует. Попробуйте другой");
            return "registration";
        } catch (DublicatedSymbolException e) {
            model.addAttribute("error", "Вы ввели слишком много одинаковых символов");
            return "registration";
        } catch (SmallPasswodsException e) {
            model.addAttribute("error", "Пароль слишком короткий");
            return "registration";
        }
    }
}
