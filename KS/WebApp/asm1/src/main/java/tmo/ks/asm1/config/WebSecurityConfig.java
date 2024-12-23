package tmo.ks.asm1.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.http.HttpServletResponse;
import tmo.ks.asm1.service.CustomUserDetailsService;
import tmo.ks.asm1.service.DatabaseService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		Map<String, String> permissions = DatabaseService.instance.URLPermissionMap();

		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(request -> {
					request.requestMatchers("/login").permitAll();
					permissions.forEach((url, permission) -> request.requestMatchers(url).hasAuthority(permission));
					request.anyRequest().authenticated();
				})
				.formLogin(form -> form.loginPage("/login")
						.defaultSuccessUrl("/")
						.failureUrl("/login?error=true")
						.permitAll())
				.logout(config -> config
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login"))
				.exceptionHandling(exception -> exception
						.accessDeniedHandler(accessDeniedHandler()));

		return http.build();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return (request, response, accessDeniedException) -> {
			if ("/".equals(request.getRequestURI())) {
				response.sendRedirect("/forbiddenNoAuth");
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
			}
		};
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