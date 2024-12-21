package tmo.ks.asm1.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmo.ks.asm1.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccount(String account);
    Account findById(int id);
}