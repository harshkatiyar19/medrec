package com.example.drWrite.services;

import com.example.drWrite.repository.DepartmentsRepository;
import com.example.drWrite.repository.OrganizationAffiliationDetailsRepository;
import com.example.drWrite.repository.OrganizationDetailsRepository;
import com.example.drWrite.repository.OrganizationTimingsRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    private final OrganizationDetailsRepository organizationDetailsRepository;
    private final OrganizationAffiliationDetailsRepository organizationAffiliationDetailsRepository;
    private final OrganizationTimingsRepository organizationTimingsRepository;
    private final DepartmentsRepository departmentsRepository;

    public OrganizationService(OrganizationDetailsRepository organizationDetailsRepository, OrganizationAffiliationDetailsRepository organizationAffiliationDetailsRepository, OrganizationTimingsRepository organizationTimingsRepository, DepartmentsRepository departmentsRepository) {
        this.organizationDetailsRepository = organizationDetailsRepository;
        this.organizationAffiliationDetailsRepository = organizationAffiliationDetailsRepository;
        this.organizationTimingsRepository = organizationTimingsRepository;
        this.departmentsRepository = departmentsRepository;
    }



}