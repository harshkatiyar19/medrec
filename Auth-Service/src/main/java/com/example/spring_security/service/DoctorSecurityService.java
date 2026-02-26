package com.example.spring_security.service;

import com.example.drWrite.repository.DoctorRepository;
import com.example.shared_library.entity.doctor.Doctor;
import com.example.shared_library.entity.patient.PatientUserDetails;
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
    }
}
