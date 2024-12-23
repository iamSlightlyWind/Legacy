package tmo.ks.asm1.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tmo.ks.asm1.entity.Permission;
import tmo.ks.asm1.service.DatabaseService;

@RestController
public class PermissionRestController {
    @PostMapping("/api/user/permission/getAll")
    public List<Permission> getAllPermissions() {
        return DatabaseService.instance.permissionRepository.findAll();
    }
}
