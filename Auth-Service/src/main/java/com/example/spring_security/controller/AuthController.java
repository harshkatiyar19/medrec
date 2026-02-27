package com.example.spring_security.controller;

import com.example.drWrite.dto.RegisterDoctorDto;
import com.example.spring_security.dto.LoginRequest;
import com.example.spring_security.dto.RegisterAdminDto;
import com.example.spring_security.dto.RegisterPatientDto;
import com.example.spring_security.service.AdminSecurityService;
import com.example.spring_security.service.DoctorSecurityService;
import com.example.spring_security.service.PatientSecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AdminSecurityService adminSecurityService;
    private final DoctorSecurityService doctorSecurityService;
    private final PatientSecurityService patientSecurityService;
    private final AuthenticationManager authenticationManager;

    public AuthController(
            AdminSecurityService adminSecurityService, DoctorSecurityService doctorSecurityService, PatientSecurityService patientSecurityService, AuthenticationManager authenticationManager) {
        this.adminSecurityService = adminSecurityService;
        this.doctorSecurityService = doctorSecurityService;
        this.patientSecurityService = patientSecurityService;
        this.authenticationManager = authenticationManager;
    }

    // ✅ Register new user
    @PostMapping("/register/doctor")
    public ResponseEntity<String> registerDoctor(@RequestBody RegisterDoctorDto request) {
        doctorSecurityService.register(request);
        return ResponseEntity.ok("Doctor registered successfully");
    }

    @PostMapping("/register/patient")
    public ResponseEntity<String> registerPatient(@RequestBody RegisterPatientDto request) {
        patientSecurityService.register(request);
        return ResponseEntity.ok("Patient registered successfully");
    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterAdminDto request) {
        adminSecurityService.register(request);
        return ResponseEntity.ok("Admin registered successfully");
    }

    // ✅ Login manually (for REST-based login)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.identifier(),
                        request.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("Login successful");
    }/*
Here is a clear, simple, and complete explanation of your login method — line by line — exactly how Spring Security processes it internally.

⸻

🔐 What Your Login Method Does

@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest request) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.identifier(),
                    request.password()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return ResponseEntity.ok("Login successful");
}


⸻

🧩 1. @PostMapping("/login")

This maps the method to:

POST /login

So clients send:
	•	Identifier (adminId / doctorId / patientId / email / phone)
	•	Password

⸻

🧩 2. Taking the Body: @RequestBody LoginRequest request

Spring converts JSON into your LoginRequest record/class.

Example input:

{
  "identifier": "DOC123",
  "password": "pass123"
}


⸻

🧩 3. Calling AuthenticationManager

Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                request.identifier(),
                request.password()
        )
);

This is the most important line.

✔ Spring Security Process (Internally)
	1.	Create an Authentication token

new UsernamePasswordAuthenticationToken(username, password)

This is an unauthenticated token (no roles yet).

	2.	Pass token to AuthenticationManager
	3.	AuthenticationManager tries your providers in order:
	•	adminProvider → tries AdminSecurityService.loadUserByUsername()
	•	patientProvider → tries PatientSecurityService
	•	doctorProvider → tries DoctorSecurityService
	4.	When the correct provider finds the user:
	•	Loads password, roles, authorities
	•	Compares password using PasswordEncoder
	•	Returns a fully authenticated Authentication object

✔ If password wrong → throws BadCredentialsException

✔ If no provider matches user → UsernameNotFoundException

⸻

🧩 4. Set Authentication into SecurityContext

SecurityContextHolder.getContext().setAuthentication(authentication);

This tells Spring:

“This user is now officially logged in for this request.”

Meaning:
	•	Spring Security remembers the user for the session (if using sessions)
	•	Controllers can use @AuthenticationPrincipal to get user details
	•	SecurityContext now contains username, roles, authorities

⸻

🧩 5. Return Response

return ResponseEntity.ok("Login successful");

If everything worked → send 200 OK.

⸻

🧠 Complete Flow in One Illustration
	1.	Request comes to /login
	2.	UsernamePasswordAuthenticationToken created
	3.	AuthenticationManager loops through providers
	4.	Correct provider loads user
	5.	Password validated
	6.	Roles added
	7.	Authentication stored in SecurityContext
	8.	Success response returned

⸻

🔥 In your case (Multiple Providers)

AuthenticationManager checks:
	1.	Admin provider
→ adminSecurityService → admin table
	2.	Patient provider
→ patientSecurityService → patient table
	3.	Doctor provider
→ doctorSecurityService → doctor table

Which means:

✔ One /login works for ALL user types

✔ The correct provider handles the matching user

✔ Everything else happens automatically

⸻

If you want, I can also explain:

✅ How to return JWT tokens instead of simple text
✅ How Authentication works inside filters
✅ How to extract roles and send them back in login response
Just tell me!
    */

    // ✅ Get current logged-in user
    @GetMapping("/me")
    public ResponseEntity<String> currentUser(Authentication authentication) {

        return ResponseEntity.ok("Logged in as: " + authentication.getName());
    }

    // ✅ Logout (session-based)
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) throws Exception {

        request.logout(); // clears authentication & session

        return ResponseEntity.ok("Logged out successfully");
    }
    /*
Here is a clean and simple explanation of your logout method — line by line — and what happens internally in Spring Security.

⸻

🔐 Your Logout Method

@PostMapping("/logout")
public ResponseEntity<String> logout(HttpServletRequest request) throws Exception {

    request.logout(); // clears authentication & session

    return ResponseEntity.ok("Logged out successfully");
}


⸻

🧩 1. @PostMapping("/logout")

This maps the method to:

POST /logout

Whenever the client calls this endpoint, the logout logic runs.

⸻

🧩 2. HttpServletRequest request

Spring injects the current HTTP request so you can call request.logout().

⸻

🧩 3. request.logout() — What it Actually Does

This is the Servlet Container Logout, not Spring’s logout URL.

Internally it does:

✔ 1. Clears the current authentication

It removes the Authentication object stored in:

SecurityContextHolder.getContext()

So Spring Security no longer sees the user as logged in.

✔ 2. Invalidates the session

The active HTTP session is destroyed:

JSESSIONID removed
session.invalidate()

✔ 3. Removes stored security context

Clears any cached session-based login information.

✔ 4. Executes Spring Security LogoutHandlers

(if configured)

✔ 5. Makes the user anonymous

After logout, the user is treated as anonymous for future requests.

⸻

🔥 Visual Understanding of Logout Flow

⸻

🧩 4. return ResponseEntity.ok("Logged out successfully");

Sends a 200 OK response to the client confirming logout.

⸻

🔍 Difference Between request.logout() and Spring Security’s /logout URL

Method	How It Works	Usage
request.logout()	Servlet container logout	Used inside controllers
Spring .logoutUrl("/api/auth/logout")	Spring Security LogoutFilter	Used automatically if configured

If both exist → Spring Security prefers your configured logout URL.

⸻

🧠 When Should You Use request.logout()?

Use it when:

✔ You want manual logout inside your controller
✔ You want to invalidate session programmatically
✔ You want cleaner API logout for Postman/mobile apps
✔ You disabled Spring’s default form-based logout

⸻

🟢 Summary (Easy to Remember)

request.logout() does:
	•	❌ remove authentication
	•	❌ delete SecurityContext
	•	❌ kill session
	•	❌ remove JSESSIONID cookie
	•	✔ same effect as Spring Security logout filter
	•	✔ perfect for REST APIs

⸻

If you want, I can also show:

✅ How to logout with JWT
✅ How to blacklist JWT tokens
✅ How to logout all sessions of a user
Just tell me!
    * */
}