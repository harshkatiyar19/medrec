package com.example.drWrite.repository;

import com.example.shared_library.entity.doctor.OrganizationAffiliationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationAffiliationDetailsRepository extends JpaRepository<OrganizationAffiliationDetails,Long> {
}
