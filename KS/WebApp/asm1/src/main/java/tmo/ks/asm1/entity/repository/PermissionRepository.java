package tmo.ks.asm1.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmo.ks.asm1.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Permission findById(int id);
}