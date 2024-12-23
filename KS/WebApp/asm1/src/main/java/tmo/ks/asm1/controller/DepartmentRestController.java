package tmo.ks.asm1.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tmo.ks.asm1.entity.Department;
import tmo.ks.asm1.service.DatabaseService;

@RestController
public class DepartmentRestController {
    @PostMapping("/api/user/department/getAll")
    public List<Department> getAllDepartments() {
        return DatabaseService.instance.departmentRepository.findAll();
    }
}
