package tmo.ks.asm1.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tmo.ks.asm1.entity.Department;
import tmo.ks.asm1.service.DatabaseService;

@RestController
@RequestMapping("/api/department")
public class DepartmentRestController {
    @PostMapping("/getAll")
    public List<Department> getAllDepartments() {
        return DatabaseService.instance.departmentRepository.findAll();
    }
}
