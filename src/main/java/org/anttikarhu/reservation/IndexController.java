package org.anttikarhu.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("appName", "Reservation app");
        model.addAttribute("scriptBundles", Collections.singletonList("http://localhost:8081/bundle.js"));
        return "index";
    }
}