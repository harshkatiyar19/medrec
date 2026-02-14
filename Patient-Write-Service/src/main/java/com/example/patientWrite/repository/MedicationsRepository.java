package com.example.patientWrite.repository;

import com.example.shared_library.entity.patient.Medications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationsRepository extends JpaRepository<Medications,Long> {
}
