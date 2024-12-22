package tmo.ks.asm1.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmo.ks.asm1.entity.Account;
import tmo.ks.asm1.service.DatabaseService;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccount(String account);

    default String getAccountPermission(Account account) {
        return DatabaseService.instance.permissionRepository.findById(account.getId()).get().getName();
    }
}