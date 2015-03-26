package lv.j2304z.testapp.controllers;

import lv.j2304z.testapp.dto.WeatherDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jekaterina.zaiceva on 26.03.15.
 */
@Controller
public class JeisonController {
    @RequestMapping(method = RequestMethod.GET, value = "/weather")
    public @ResponseBody
    WeatherDTO getLoginPage() {
        WeatherDTO result = new WeatherDTO();
        result.setTemperature(10);
        result.setWindSpeed(3);
        return result;
    }

}
