package lv.j2304z.testapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
