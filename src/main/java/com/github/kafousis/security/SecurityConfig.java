package com.github.kafousis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Cross-site request forgery (CSRF) is a web security vulnerability
    // https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/csrf.html
    // https://www.baeldung.com/spring-security-csrf

    // If our stateless API uses token-based authentication, such as JWT,
    // we don't need CSRF protection, and we must disable it

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http.build();
    }

}
