package com.example.patientWrite.repository;

import com.example.shared_library.entity.patient.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact,Long> {
}
