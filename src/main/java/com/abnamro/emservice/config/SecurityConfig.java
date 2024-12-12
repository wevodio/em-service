package com.abnamro.emservice.config;

import com.abnamro.emservice.filter.ApiKeyAuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final ApiKeyAuthFilter apiKeyAuthFilter;

    public SecurityConfig(ApiKeyAuthFilter apiKeyAuthFilter) {
        this.apiKeyAuthFilter = apiKeyAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()

                )
                .addFilterBefore(apiKeyAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class); // Add the custom filter

        return http.build();
    }
}
