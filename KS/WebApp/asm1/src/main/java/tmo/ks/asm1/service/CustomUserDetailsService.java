package tmo.ks.asm1.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomUserDetailsService implements UserDetailsService {
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserFromDatabase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Optional<UserDetails> getUserFromDatabase(String username) {
        if ("admin".equals(username)) {
            return Optional.of(User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN")
                    .build());
        } else if ("user".equals(username)) {
            return Optional.of(User.builder()
                    .username("user")
                    .password(passwordEncoder().encode("user"))
                    .roles("USER")
                    .build());
        }
        return Optional.empty();
    }

    /* private Optional<UserDetails> getUser(String username, String password) {
        int maxId = DatabaseService.getAccountCount();
        for (int id = 1; id <= maxId; id++) {
            Account account = DatabaseService.getAccountById(id);
            if (account.getAccount().equals(username)) {
                return Optional.of(User.builder()
                        .username(account.getAccount())
                        .password(passwordEncoder().encode(account.getPassword()))
                        .roles("
            }
        }
    } */
}
