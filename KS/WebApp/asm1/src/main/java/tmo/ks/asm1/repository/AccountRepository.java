package tmo.ks.asm1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmo.ks.asm1.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}