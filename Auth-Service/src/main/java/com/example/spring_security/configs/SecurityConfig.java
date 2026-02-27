package com.example.spring_security.configs;

import com.example.spring_security.service.AdminSecurityService;
import com.example.spring_security.service.DoctorSecurityService;
import com.example.spring_security.service.PatientSecurityService;
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
    private final PatientSecurityService patientSecurityService;
    private final DoctorSecurityService doctorSecurityService;

    public SecurityConfig(AdminSecurityService adminSecurityService, PatientSecurityService patientSecurityService, DoctorSecurityService doctorSecurityService) {
        this.adminSecurityService = adminSecurityService;
        this.patientSecurityService = patientSecurityService;
        this.doctorSecurityService = doctorSecurityService;
    }



    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder
    ) throws Exception {

        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);


        DaoAuthenticationProvider adminProvider = new DaoAuthenticationProvider(adminSecurityService);
        adminProvider.setPasswordEncoder(passwordEncoder);

        DaoAuthenticationProvider patientProvider = new DaoAuthenticationProvider(patientSecurityService);
        patientProvider.setPasswordEncoder(passwordEncoder);

        DaoAuthenticationProvider doctorProvider = new DaoAuthenticationProvider(doctorSecurityService);
        doctorProvider.setPasswordEncoder(passwordEncoder);

        builder.authenticationProvider(adminProvider);
        builder.authenticationProvider(patientProvider);
        builder.authenticationProvider(doctorProvider);

        return builder.build();
    }
/*
You do NOT always need multiple DaoAuthenticationProvider —
you only need them when your architecture forces it.

Below is the real reason in the simplest but most correct explanation.

⸻

✅ Why You Sometimes Need Multiple DaoAuthenticationProvider

DaoAuthenticationProvider works with exactly one UserDetailsService.

So if you have multiple user types
and each user type uses a DIFFERENT UserDetailsService,
then you must create multiple providers.

⸻

🎯 RULE: One Provider = One UserDetailsService

Situation	Need multiple providers?	Why
All users live in ONE table	❌ No	One UserDetailsService can load them
Users live in DIFFERENT tables	✅ Yes	Each table needs its own UserDetailsService
Login flow is SAME for all	❌ No	Single provider works
Login flow is DIFFERENT for each	✅ Yes	Each flow needs its own provider


⸻

📌 When You MUST Create Multiple DaoAuthenticationProvider

If you have this:

✔️ Admin table

✔️ Doctors table

✔️ Patients table

Then you will have:
	•	AdminSecurityService implements UserDetailsService
	•	DoctorSecurityService implements UserDetailsService
	•	PatientSecurityService implements UserDetailsService

Since each provider accepts only ONE UserDetailsService,
you must do this:

DaoAuthenticationProvider adminProvider = new DaoAuthenticationProvider();
adminProvider.setUserDetailsService(adminSecurityService);

DaoAuthenticationProvider doctorProvider = new DaoAuthenticationProvider();
doctorProvider.setUserDetailsService(doctorSecurityService);

DaoAuthenticationProvider patientProvider = new DaoAuthenticationProvider();
patientProvider.setUserDetailsService(patientSecurityService);

Otherwise Spring cannot decide which DB table to search for the user.

⸻

📌 Why You Cannot Use ONE DaoAuthenticationProvider for Multiple Services

DaoAuthenticationProvider internally calls:

userDetailsService.loadUserByUsername(...)

There is NO mechanism like:

try Admin service
then try Doctor service
then try Patient service

It only calls ONE UserDetailsService.

Therefore, if you have multiple user types stored separately →
you MUST have multiple providers.

⸻

🧠 Visual Understanding

⸻

🟢 When You DO NOT Need Multiple Providers

If instead you design your DB like:

users table

id	username	password	role
1	admin01	…	ADMIN
2	doc01	…	DOCTOR
3	pat01	…	PATIENT

Then ONE UserDetailsService can return:
	•	Admin role
	•	Doctor role
	•	Patient role

And you only need:

builder.userDetailsService(userDetailsService)
       .passwordEncoder(passwordEncoder);


⸻

🔥 FINAL ANSWER (Simplified)

✔️ You need multiple DaoAuthenticationProvider

ONLY IF different types of users are stored in different tables and have different UserDetailsService implementations.

❌ You do NOT need multiple providers

if you store all users in one table and handle roles inside UserDetails.

⸻

If you want, I can tell you which architecture is better for hospital / health systems and help you build the correct one.
* */

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
        /*
        Below is a clear, line-by-line explanation of your Spring Security configuration.
        Images are included where visual understanding helps.

        ⸻

        🔐 Spring Security Configuration — Explained Line by Line

        ⸻

        ✔️ http.csrf(AbstractHttpConfigurer::disable)

        What it does:
        Disables CSRF protection.

        Why:
            •	CSRF is required for browser-based form submissions using cookies.
            •	Your API likely uses JWT or stateless tokens, or Postman/mobile clients.
            •	For REST APIs, CSRF is not needed.

        ⸻

        ✔️ http.httpBasic(AbstractHttpConfigurer::disable)

        What it does:
        Disables HTTP Basic Auth (username/password popup dialog).

        Why:
            •	You don’t want browser popups.
            •	You are implementing your own login API (/api/auth/login).

        ⸻

        ✔️ http.formLogin(AbstractHttpConfigurer::disable)

        What it does:
        Disables Spring’s default /login form page.

        Why:
            •	You are using custom login controller, not Spring’s login page.

        ⸻

        🔓 Authorization Rules

        ✔️ authorizeHttpRequests(auth -> auth... )

        Controls which URLs require login and which don’t.

        1. .requestMatchers("/api/auth/login","/api/auth/register").permitAll()

        These paths do not require authentication.
            •	/api/auth/login → Users must be able to log in without being logged in
            •	/api/auth/register → New users must be able to register

        Everything else → must be authenticated.

        2. .anyRequest().authenticated()

        Every other API endpoint requires authentication.

        ⸻

        🚪 Logout Config

        ✔️ logout(logout -> logout.logoutUrl("/api/auth/logout"))

        Defines custom logout URL.

        ✔️ .invalidateHttpSession(true)

        Destroys the user’s session entry in the server.

        ✔️ .deleteCookies("JSESSIONID")

        Removes session cookie from the client browser.

        Why:
            •	Ensures complete logout.
            •	Prevents session reuse.

        ⸻

        🧑‍🤝‍🧑 Session Management

        ✔️ .sessionManagement(session -> session.maximumSessions(1))

        Limits user to one active session at a time.

        Meaning:
            •	If the same user logs in somewhere else → previous session is invalidated.

        Useful for:
            •	Banking apps
            •	Admin panels
            •	Security-sensitive dashboards

        ⸻

        ✔️ .build()

        Builds and returns the final SecurityFilterChain.

        ⸻

        📌 Full Summary Table

        Code Line	Meaning
        .csrf(disable)	Disable CSRF for REST APIs
        .httpBasic(disable)	Disable browser login popup
        .formLogin(disable)	Disable default login page
        .requestMatchers(...).permitAll()	Allow login/register without authentication
        .anyRequest().authenticated()	Everything else requires login
        .logoutUrl("/api/auth/logout")	Custom logout endpoint
        .invalidateHttpSession(true)	Kill server session
        .deleteCookies("JSESSIONID")	Remove session cookie
        .maximumSessions(1)	Only 1 session per user
        .build()	Build filter chain


        ⸻

        If you want, I can also explain:
        ✅ How JWT replaces sessions
        ✅ How filter chain runs internally
        ✅ How to add roles (ADMIN, USER)
        ✅ How to add multiple SecurityFilterChain configurations (admin + user)
        * */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
