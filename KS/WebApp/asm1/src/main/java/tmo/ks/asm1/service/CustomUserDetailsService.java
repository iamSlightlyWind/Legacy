package tmo.ks.asm1.service;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserFromDatabase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Optional<UserDetails> getUserFromDatabase(String username) {
        // Simulate database lookup for the user by username
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
}
