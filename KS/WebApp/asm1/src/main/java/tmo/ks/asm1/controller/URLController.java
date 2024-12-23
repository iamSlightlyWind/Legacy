package tmo.ks.asm1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class URLController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String root() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return root();
    }

    @GetMapping("/fragments/admin/{fragmentName}")
    public String loadAdminFragment(@PathVariable String fragmentName) {
        return fragmentName;
    }

    @GetMapping("/fragments/user/{fragmentName}")
    public String loadUserFragment(@PathVariable String fragmentName) {
        return fragmentName;
    }
}