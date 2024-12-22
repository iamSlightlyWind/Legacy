package tmo.ks.asm1.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tmo.ks.asm1.entity.Employee;
import tmo.ks.asm1.service.DatabaseService;

import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/fragments/{fragmentName}")
    public String loadFragment(@PathVariable String fragmentName) {
        return fragmentName;
    }

    @PostMapping("/api/employee/getAll")
    public List<Employee> getAllEmployees() {
        return DatabaseService.instance.employeeRepository.findAll();
    }
}