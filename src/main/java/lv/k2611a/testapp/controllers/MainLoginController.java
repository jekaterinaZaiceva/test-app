package lv.k2611a.testapp.controllers;

import lv.k2611a.testapp.domain.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by jekaterina.zaiceva on 18.11.2014.
 */
@Controller
public class MainLoginController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "index";
    }
}
