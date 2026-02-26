package com.example.patientWrite.repository;

import com.example.shared_library.entity.patient.PatientUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientUserDetailsRepository extends JpaRepository<PatientUserDetails,Long> {
    Optional<PatientUserDetails> findByPatientId(Long username);

    Optional<PatientUserDetails> findByEmail(String email);

    Optional<PatientUserDetails> findByPhoneNumber(String phoneNumber);
}
