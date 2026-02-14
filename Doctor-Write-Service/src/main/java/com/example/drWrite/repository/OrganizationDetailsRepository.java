package com.example.drWrite.repository;

import com.example.shared_library.entity.doctor.OrganizationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationDetailsRepository extends JpaRepository<OrganizationDetails,Long> {
}