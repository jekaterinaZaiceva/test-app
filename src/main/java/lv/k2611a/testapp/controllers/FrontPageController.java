package lv.k2611a.testapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class FrontPageController {


    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public String getMainPage(Model model, @PathVariable Long userId) {
        model.addAttribute("username", "Vasya Pupkin");
        return "hello";
    }


}
