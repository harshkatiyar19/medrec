package com.example.spring_security.configs;

import com.example.spring_security.service.AdminSecurityService;
import com.example.spring_security.service.SecurityUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final AdminSecurityService adminSecurityService;
    private final SecurityUserService userDetailsService2;

    public SecurityConfig( AdminSecurityService adminSecurityService, SecurityUserService userDetailsService2) {
        this.adminSecurityService = adminSecurityService;
        this.userDetailsService2 = userDetailsService2;
    }



    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder
    ) throws Exception {

        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);


        DaoAuthenticationProvider adminProvider = new DaoAuthenticationProvider(adminSecurityService);
        adminProvider.setPasswordEncoder(passwordEncoder);

        DaoAuthenticationProvider customerProvider = new DaoAuthenticationProvider(userDetailsService2);
        customerProvider.setPasswordEncoder(passwordEncoder);

        builder.authenticationProvider(adminProvider);
        builder.authenticationProvider(customerProvider);

        return builder.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login","/api/auth/register").permitAll()
                        .anyRequest().authenticated()
                )

                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )

                .sessionManagement(session ->
                        session.maximumSessions(1)
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
