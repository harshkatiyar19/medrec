package com.example.patientWrite.repository;

import com.example.shared_library.entity.patient.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepository extends JpaRepository<Reports,Long> {
}
