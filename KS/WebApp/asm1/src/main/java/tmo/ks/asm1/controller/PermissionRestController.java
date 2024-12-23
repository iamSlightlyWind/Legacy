package tmo.ks.asm1.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tmo.ks.asm1.entity.Permission;
import tmo.ks.asm1.service.DatabaseService;

@RestController
@RequestMapping("/api/permission")
public class PermissionRestController {
    @PostMapping("/user/getAll")
    public List<Permission> getAllPermissions() {
        return DatabaseService.instance.permissionRepository.findAll();
    }
}
