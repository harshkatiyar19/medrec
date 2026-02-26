package com.example.drRead.controller;


import com.example.drRead.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @GetMapping("/list")
    public ResponseEntity<?> getOrganizations(@Valid @RequestBody ListOrganizationInfoDto listOrganizationInfoDto){
        return null;
    }

    @GetMapping("/filter-data")
    public ResponseEntity<?> getOrganizationFilterData(@Valid @RequestBody FilterOrganizationDto filterOrganizationDto){
        return null;
    }

    @GetMapping("/list/parent-organization")
    public ResponseEntity<?> getParentOrganizations(@Valid @RequestBody ListOrganizationDto listOrganizationDto){
        return null;
    }

    @GetMapping("/list/child-organization")
    public ResponseEntity<?> getChildOrganizations(@Valid @RequestBody ListOrganizationDto listOrganizationDto){
        return null;
    }

    @GetMapping("/list/organization-timings")
    public ResponseEntity<?> getOrganizationTimings(@Valid @RequestBody ListOrganizationDto listOrganizationDto){
        return null;
    }

    @GetMapping("/list/departments")
    public ResponseEntity<?> getOrganizationDepartments(@Valid @RequestBody ListDepartmentDto listOrganizationDto){
        return null;
    }

    @GetMapping("/list/department/doctors")
    public ResponseEntity<?> getOrganizationDepartmentDoctors(@Valid @RequestBody ListDepartmentDoctorDto listOrganizationDto){
        return null;
    }
}
