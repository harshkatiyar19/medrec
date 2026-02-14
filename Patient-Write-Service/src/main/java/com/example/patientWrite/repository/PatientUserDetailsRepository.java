package com.example.patientWrite.repository;

import com.example.shared_library.entity.patient.PatientUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientUserDetailsRepository extends JpaRepository<PatientUserDetails,Long> {
}
