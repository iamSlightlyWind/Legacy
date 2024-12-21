package tmo.ks.asm1.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tmo.ks.asm1.entity.Account;
import tmo.ks.asm1.entity.AccountPermission;
import tmo.ks.asm1.entity.AccountPermissionId;

public interface AccountPermissionRepository extends JpaRepository<AccountPermission, AccountPermissionId> {
    List<AccountPermission> findByAccount(Account account);
}