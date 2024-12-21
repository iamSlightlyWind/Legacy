package tmo.ks.asm1.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmo.ks.asm1.entity.AccountPermission;
import tmo.ks.asm1.entity.AccountPermissionId;

public interface AccountPermissionRepository extends JpaRepository<AccountPermission, AccountPermissionId> {
}