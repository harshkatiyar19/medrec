package com.example.drWrite.repository;

import com.example.shared_library.entity.doctor.OrganizationTimings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationTimingsRepository extends JpaRepository<OrganizationTimings,Long> {
}
