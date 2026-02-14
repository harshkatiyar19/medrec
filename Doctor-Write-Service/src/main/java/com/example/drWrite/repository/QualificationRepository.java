package com.example.drWrite.repository;

import com.example.shared_library.entity.doctor.Qualifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualifications,Long> {
}
