package com.example.kyrgyzstancentralmedicalcard.config;

import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/api/auth/register").permitAll()
                            .requestMatchers("/api/auth/login").permitAll()
                            .anyRequest().authenticated();
                }).sessionManagement(manager ->
                        manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public @Nullable String encode(@Nullable CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
}
