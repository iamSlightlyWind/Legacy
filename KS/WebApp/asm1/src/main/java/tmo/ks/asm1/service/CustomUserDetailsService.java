package tmo.ks.asm1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tmo.ks.asm1.entity.Account;
import tmo.ks.asm1.entity.AccountPermission;

public class CustomUserDetailsService implements UserDetailsService {
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getAccount(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Optional<UserDetails> getAccount(String username) {
        Account match = DatabaseService.instance.accountRepository.findByAccount(username);
        if (match == null) {
            return Optional.empty();
        }

        List<String> permissions = new ArrayList<>();
        for (AccountPermission accountPermission : DatabaseService.instance.accountPermissionRepository.findByAccount(match)) {
            permissions.add(accountPermission.getPermission().getName());
        }

        return Optional.of(User.builder()
                .username(match.getAccount())
                .password(passwordEncoder().encode(match.getPassword()))
                .authorities(permissions.toArray(new String[0]))
                .build());
    }
}
