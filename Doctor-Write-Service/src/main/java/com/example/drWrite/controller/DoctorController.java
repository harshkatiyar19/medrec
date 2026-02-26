package com.example.drWrite.controller;

import com.example.drWrite.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @PostMapping("/register")
    public RequestEntity<?> registerDoctor(@Valid @RequestBody RegisterDoctorDto registerDoctorDto){
        return null;
    }

    @PutMapping("/update")
    public RequestEntity<?> updateDoctor(@Valid @RequestBody UpdateDoctorDto updateDoctorDto){
        return null;
    }

    @PostMapping("/doctor-department/register")
    public RequestEntity<?> registerDoctorDepartment(@Valid @RequestBody RegisterDoctorDepartmentDto registerDoctorDepartmentDto){
        return null;
    }

    @PostMapping("/doctor-department-timings/register")
    public RequestEntity<?> registerDoctorDepartmentTimings(@Valid @RequestBody RegisterDoctorDepartmentDto registerDoctorDepartmentDto){
        return null;
    }

    @PutMapping("/doctor-department-timings/update")
    public RequestEntity<?> updateDoctorDepartmentTimings(@Valid @RequestBody UpdateDoctorDepartmentDto updateDoctorDepartmentDto){
        return null;
    }

    @PostMapping("/qualifications/add")
    public RequestEntity<?> addQualifications(@Valid @RequestBody QualificationsDto qualificationsDto){
        return null;
    }
} 
