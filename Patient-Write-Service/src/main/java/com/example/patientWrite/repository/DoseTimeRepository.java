package com.example.patientWrite.repository;

import com.example.shared_library.entity.patient.DoseTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseTimeRepository extends JpaRepository<DoseTime,Long> {
}
