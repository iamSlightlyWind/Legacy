package tmo.ks.asm1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tmo.ks.asm1.entity.Department;
import tmo.ks.asm1.entity.Employee;
import tmo.ks.asm1.service.DatabaseService;
import java.util.List;

@RestController
public class RestfulController {

    @PostMapping("/api/employee/getAll")
    public List<Employee> getAllEmployees() {
        /* return DatabaseService.instance.employeeRepository.findAll(); */
        List<Employee> employees = DatabaseService.instance.employeeRepository.findAll();
        return employees;
    }

    @PostMapping("/api/department/getAll")
    public List<Department> getAllDepartments() {
        return DatabaseService.instance.departmentRepository.findAll();
    }
}