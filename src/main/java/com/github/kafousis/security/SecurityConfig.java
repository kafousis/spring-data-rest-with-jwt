package com.github.kafousis.security;

import com.github.kafousis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // default authentication manager created by spring security
    // picks up userDetailsService and passwordEncoder automatically
    // as long as they are registered as Beans

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // no session will be created or used by Spring Security
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                // Cross-site request forgery (CSRF) is a web security vulnerability
                // If our stateless API uses token-based authentication, such as JWT,
                // we don't need CSRF protection, and we must disable it
                .csrf().disable()

                .authorizeRequests()
                    .antMatchers(GET, "/api/privileges", "/api/privileges/**").hasAnyRole("ADMIN", "MANAGER")
                    .antMatchers(POST, "/api/privileges").hasRole("ADMIN")
                    .antMatchers(PUT, "/api//privileges/*", "/api/privileges/**").hasRole("ADMIN")
                    .antMatchers(PATCH, "/api/privileges/*", "/api/privileges/**").hasRole("ADMIN")
                    .antMatchers(DELETE, "/api/privileges/*", "/api/privileges/**").hasRole("ADMIN")
                    .antMatchers("/api/roles", "/api/roles/*", "/api/roles/**").hasAnyRole("ADMIN", "MANAGER")

                    .antMatchers(GET, "/token/refresh", "/token/refresh/**").permitAll()
                    // .anyRequest().authenticated() is blocking access to default /error page
                    // the line below permits returning 403 Forbidden with non-empty body
                    .antMatchers("/error").permitAll()
                .anyRequest().authenticated()

                .and()

                // 401_Unauthorized is returned when the client provides no credentials or invalid credentials
                // 403_Forbidden is returned when a client has valid credentials but not enough privileges to perform an action on a resource

                // return 401 UNAUTHORIZED instead of 403 FORBIDDEN when no credentials provided
                .exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint())

                .and()

                // local authenticationManager can be accessed in a custom DSL
                // we need that instance for the authentication filter
                .apply(new JwtConfigurer(userRepository));

        return http.build();
    }
}
