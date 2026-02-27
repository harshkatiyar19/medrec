package com.example.spring_security.service;

import com.example.patientWrite.repository.PatientUserDetailsRepository;
import com.example.shared_library.entity.admin.AdminUserDetails;
import com.example.shared_library.entity.patient.PatientUserDetails;
import com.example.spring_security.dto.RegisterPatientDto;
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
        return null;
    }

    public void register(RegisterPatientDto request) {
    }
}
