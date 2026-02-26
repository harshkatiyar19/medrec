package com.example.drWrite.controller;

import com.example.drWrite.dto.*;
import com.example.drWrite.services.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerOrganization(@Valid @RequestBody RegisterOrganizationDto registerOrganizationDto){
        return null;
    }

    @PutMapping("/update-details")
    public ResponseEntity<?> updateOrganization(@Valid @RequestBody UpdateOrganizationDto updateOrganizationDto){
        return null;
    }

    @PostMapping("/departments/register")
    public ResponseEntity<?> registerOrganizationDepartments(@Valid @RequestBody RegisterOrganizationDepartmentDto registerOrganizationDepartmentDto){
        return null;
    }

    @PutMapping("/departments/update")
    public ResponseEntity<?> updateOrganizationDepartments(@Valid @RequestBody UpdateOrganizationDepartmentDto updateOrganizationDepartmentDto){
        return null;
    }

    @PostMapping("/register-timings")
    public ResponseEntity<?> registerOrganizationTimings(@Valid @RequestBody RegisterOrganizationTimingDto registerOrganizationTimingDto){
        return null;
    }

    @PutMapping("/update-timings")
    public ResponseEntity<?> updateOrganizationTimings(@Valid @RequestBody UpdateOrganizationTimingDto updateOrganizationTimingDto){
        return null;
    }
}
