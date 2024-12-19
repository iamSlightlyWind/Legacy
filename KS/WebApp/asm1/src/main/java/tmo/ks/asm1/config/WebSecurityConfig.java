package tmo.ks.asm1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;
import tmo.ks.asm1.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						request -> request.requestMatchers("/login").permitAll()
								.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.build();
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
}