package tmo.ks.asm1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tmo.ks.asm1.entity.Employee;
import tmo.ks.asm1.service.DatabaseService;

import java.util.List;

@RestController
public class RestfulController {

    @GetMapping("/api/employee/getAll")
    public List<Employee> getAllEmployees() {
        return DatabaseService.instance.employeeRepository.findAll();
    }
}