package com.job.talenMatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // createDelegatingPasswordEncoder is a wrapper class of bcryptPasswordEncoder. why use it - it Supports multiple algorithms ({noop}, {sha256}).
        // Noop(NoOpPasswordEncoder) is used in app.prop for security password so whenever we use noop, it says to security that this is plain text and don't compare it with hash conversion, compare it with string only
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection - check doc
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/register").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults()); // Enable Basic Auth - this tells spring security to expect and process user creds sent in authorization  http req(other req than mentioned abv) header for every request to protected resources

        return http.build();
    }
}
