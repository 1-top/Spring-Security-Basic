package com.harrybro.security.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(defaultValue = "false") boolean error, Model model) {
        if (error)
            model.addAttribute("errorMessage", "아디니아 패스워드가 올바르지 않습니다.");

        return "login";
    }
}
