package tmo.ks.asm1.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @GetMapping
    public Map<String, String> getEmployee() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello");
        return response;
    }
}