package tmo.ks.asm1.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class URLService {
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

    @GetMapping("/fragments/{fragmentName}")
    public String loadFragment(@PathVariable String fragmentName) {
        return fragmentName;
    }
}