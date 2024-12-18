package tmo.ks.asm1.provider;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return User.builder()
                    .username("user")
                    .password("{bcrypt}" + new BCryptPasswordEncoder().encode("user123"))
                    .roles("USER")
                    .build();
        } else if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password("{bcrypt}" + new BCryptPasswordEncoder().encode("admin123"))
                    .roles("USER", "ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}