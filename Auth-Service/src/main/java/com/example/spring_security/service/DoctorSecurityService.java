package com.example.spring_security.service;

import com.example.drWrite.dto.RegisterDoctorDto;
import com.example.drWrite.repository.DoctorRepository;
import com.example.shared_library.entity.doctor.Doctor;
import com.example.spring_security.enums.LoginIdentifierType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DoctorSecurityService implements UserDetailsService {
    private final DoctorRepository doctorRepo;

    public DoctorSecurityService(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        LoginIdentifierType type = LoginIdentifierType.from(identifier);

        Doctor doctor = switch (type) {

            case EMAIL ->  doctorRepo.findByEmail(identifier)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Email not found"));

            case PHONE ->  doctorRepo.findByPhoneNumber(identifier)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Phone number not found"));

            case DOCTOR_ID ->  doctorRepo.findByDoctorId(Long.valueOf(identifier))
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Doctor ID not found"));
            default ->throw new UsernameNotFoundException("Invalid login identifier type");
        };


        return org.springframework.security.core.userdetails.User
                .withUsername(String.valueOf(doctor.getDoctorId()))
                .password(doctor.getPassword())
//                .roles(user.getRole())
                .build();
        /*
        Here is a clear and correct explanation of every line in your code.
This snippet is building a Spring Security UserDetails object.

I’ll explain it line-by-line and also what it means internally.

⸻

✅ Code You Provided (UserDetails building)

return org.springframework.security.core.userdetails.User
        .withUsername(String.valueOf(doctor.getDoctorId()))
        .password(doctor.getPassword())
//      .roles(user.getRole())
        .build();


⸻

📌 What This Actually Does

This creates a Spring Security UserDetails object
from a Doctor entity stored in your database.

⸻

🔍 Line-by-Line Explanation

⸻

1️⃣ org.springframework.security.core.userdetails.User

This refers to Spring Security’s built-in implementation of UserDetails.

It has:
	•	username
	•	password
	•	authorities (roles)
	•	account status (locked/expired/etc)

⸻

2️⃣ .withUsername(String.valueOf(doctor.getDoctorId()))

This sets the username for Spring Security.

✔ Why use doctorId?

You are telling Spring Security:

“The doctor will log in using doctorId as the username.”

Example:
If doctorId = 42 → username = "42"

You could also use email, phone, or username instead.

⸻

3️⃣ .password(doctor.getPassword())

This sets the password that Spring compares with the login request.

Important:
	•	This must be the hashed password stored in DB.
	•	The value passed here will never be used raw — Spring uses PasswordEncoder.matches().

So if your DB has "bcrypt-hash-123" → Spring uses that.

⸻

4️⃣ // .roles(user.getRole())

This is commented out.

Normally, this line is used to assign roles/authorities such as:
	•	ADMIN
	•	DOCTOR
	•	PATIENT

Example:

.roles("DOCTOR")

Spring converts roles into:

ROLE_DOCTOR

If you remove this line entirely, the user will have no roles, only authentication but no authorization privileges.

⸻

5️⃣ .build();

This finishes creating the UserDetails object.

Spring Security now has this in memory:

username: "42"
password: "$2a$10$..."
authorities: []  (empty if you didn't set roles)

This object is returned to Spring Security so that authentication can happen.

⸻

🧠 What Happens Internally?
	1.	Login request → you send username/password
	2.	Spring calls your UserDetailsService.loadUserByUsername()
	3.	You convert your Doctor entity → Spring’s User object
	4.	Spring compares:
	•	Input password
	•	Stored password (using PasswordEncoder)
	5.	If correct → authentication successful.

⸻

📌 Why your code doesn’t include roles?

Because you commented out .roles(...).

To add role DOCTOR:

.roles("DOCTOR")

To add authorities manually:

.authorities("READ_PATIENTS", "WRITE_PRESCRIPTION")


⸻

🎯 Final Clean Example (Best Practice)

return org.springframework.security.core.userdetails.User
        .withUsername(String.valueOf(doctor.getDoctorId()))
        .password(doctor.getPassword())
        .roles("DOCTOR") // now the doctor has ROLE_DOCTOR
        .build();


⸻

If you want, I can generate the correct UserDetailsService for
Admin, Doctor, and Patient — or even show you how to store roles properly in your DB.
        * */
    }

    public void register(RegisterDoctorDto request) {

    }
}
