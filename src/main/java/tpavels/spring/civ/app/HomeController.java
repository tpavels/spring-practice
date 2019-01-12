package tpavels.spring.civ.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String greeting() {
        return "redirect:/swagger-ui.html";
    }
}
