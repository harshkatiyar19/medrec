package com.example.spring_security.service;

import com.example.patientWrite.repository.PatientUserDetailsRepository;
import com.example.shared_library.entity.patient.PatientUserDetails;
import com.example.spring_security.enums.LoginIdentifierType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatientSecurityService implements UserDetailsService {
    private final PatientUserDetailsRepository patientRepo;

    public PatientSecurityService(PatientUserDetailsRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        LoginIdentifierType type = LoginIdentifierType.from(identifier);

        PatientUserDetails patient = switch (type) {

            case EMAIL ->  patientRepo.findByEmail(identifier)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Email not found"));

            case PHONE ->  patientRepo.findByPhoneNumber(identifier)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Phone number not found"));

            case ADMIN_ID ->  patientRepo.findByPatientId(Long.valueOf(identifier))
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Admin ID not found"));

            default ->throw new UsernameNotFoundException("Invalid login identifier type");
        };


        return org.springframework.security.core.userdetails.User
                .withUsername(String.valueOf(patient.getPatientId()))
                .password(patient.getPassword())
//                .roles(user.getRole())
                .build();
    }

}
