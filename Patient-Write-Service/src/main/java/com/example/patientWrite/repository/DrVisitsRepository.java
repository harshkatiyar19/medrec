package com.example.patientWrite.repository;

import com.example.shared_library.entity.patient.DrVisits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrVisitsRepository extends JpaRepository<DrVisits,Long> {
}
