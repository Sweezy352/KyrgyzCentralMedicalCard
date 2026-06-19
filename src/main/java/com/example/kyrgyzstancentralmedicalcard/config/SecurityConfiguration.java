package com.example.kyrgyzstancentralmedicalcard.config;

import com.example.kyrgyzstancentralmedicalcard.security.JwtCore;
import com.example.kyrgyzstancentralmedicalcard.security.TokenFilter;
import com.example.kyrgyzstancentralmedicalcard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserService userService;
    private final JwtCore jwtCore;

    @Autowired
    public SecurityConfiguration(UserService userService, JwtCore jwtCore) {
        this.userService = userService;
        this.jwtCore = jwtCore;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request ->
                        new CorsConfiguration().applyPermitDefaultValues()
                ))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        //auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/register").permitAll()

                        //users
                        .requestMatchers("/api/users/get-all-users").permitAll()
                        .requestMatchers("/api/users/get-by-id/{id}").permitAll()
                        .requestMatchers("/api/users/get-by-fio").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/users/get-by-inn").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/users/{id}/assign-role").hasRole("ADMIN")
                        //qrcode
                        .requestMatchers("/api/qrcode/qr-code/users/{id}").permitAll()

                        //history
                        .requestMatchers("/api/history/create-history/{id}").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("/api/history/get-by-id/{id}").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/history/get-all-by-date").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/history/get-by-name").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/history/get-by-company-name").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/history/get-by-doc-fio").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")

                        //Diagnosis
                        .requestMatchers("/api/diagnosis/create-diagnosis/").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("/api/diagnosis/get-by-id/").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/diagnosis/get-by-name").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/diagnosis/get-all-diagnosis").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/diagnosis/update-diagnosis/").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("/api/diagnosis/get-all-by-user/{id}").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")

                        //Receipt
                        .requestMatchers("/api/receipt/create-receipt/{id}").hasAnyRole("DOCTOR")
                        .requestMatchers("/api/receipt/get-by-id/{id}").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/receipt/get-all-receipts").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/receipt/get-all-by-user/{id}").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/receipt/get-by-name").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/receipt/get-by-date").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")

                        //Allergie
                        .requestMatchers("/api/allergies/add-allergie/{id}").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("/api/allergies/get-all-allergies/{id}").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/allergies/get-all").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/allergies/get-all-by-user/{id}").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/allergies/get-all-by-name").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/allergies/get-all-by-date").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")
                        .requestMatchers("/api/allergies/get-all-by-doctor").hasAnyRole("ADMIN", "DOCTOR", "USER", "NURSE", "PHARMACIST")

                        //Organizations
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/organizations").hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/organizations/{id}").hasAnyRole("ADMIN", "CLINIC_ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/organizations/{id}").hasAnyRole("ADMIN", "CLINIC_ADMIN", "COMPANY_HR")
                        .requestMatchers("/api/organizations/{id}/staff").hasAnyRole("ADMIN", "CLINIC_ADMIN")

                        //Consents
                        .requestMatchers("/api/patients/{patientId}/consents/**").hasAnyRole("ADMIN", "USER", "DOCTOR")

                        //Visits
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/visits").hasAnyRole("DOCTOR")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/visits/{id}").hasAnyRole("ADMIN", "DOCTOR", "CLINIC_ADMIN")
                        .requestMatchers("/api/patients/{id}/visits").hasAnyRole("ADMIN", "DOCTOR", "CLINIC_ADMIN", "USER")
                        .requestMatchers("/api/organizations/{id}/visits").hasAnyRole("ADMIN", "CLINIC_ADMIN")

                        //Analytics
                        .requestMatchers("/api/analytics/clinic/my/dashboard").hasAnyRole("ADMIN", "CLINIC_ADMIN")
                        .requestMatchers("/api/analytics/employer/my/dashboard").hasAnyRole("ADMIN", "COMPANY_HR")
                        .requestMatchers("/api/analytics/clinic/**").hasAnyRole("ADMIN", "CLINIC_ADMIN")
                        .requestMatchers("/api/analytics/employer/**").hasAnyRole("ADMIN", "COMPANY_HR")

                        //Health groups (группы здоровья сотрудников)
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/health-groups").hasAnyRole("ADMIN", "CLINIC_ADMIN", "COMPANY_HR", "DOCTOR", "NURSE")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/organizations/{id}/health-groups").hasAnyRole("ADMIN", "CLINIC_ADMIN", "COMPANY_HR")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/patients/{id}/health-groups").hasAnyRole("ADMIN", "CLINIC_ADMIN", "COMPANY_HR", "DOCTOR")

                        //Access logs (лог событий доступа)
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/access-logs").hasAnyRole("ADMIN", "CLINIC_ADMIN", "COMPANY_HR", "DOCTOR", "NURSE", "PHARMACIST")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/organizations/{id}/access-logs").hasAnyRole("ADMIN", "CLINIC_ADMIN", "COMPANY_HR")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/patients/{id}/access-logs").hasAnyRole("ADMIN", "DOCTOR", "USER")

                        .anyRequest().authenticated()


                )
                .addFilterBefore(new TokenFilter(jwtCore, userService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}