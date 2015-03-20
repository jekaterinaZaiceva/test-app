package lv.j2304z.testapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jekaterina.zaiceva on 06.03.15.
 */

@Controller
public class CalculatorController {
    @RequestMapping(value = "/calculator", method = RequestMethod.GET)
    public String calculator() {
        return "calculator";
    }
}
