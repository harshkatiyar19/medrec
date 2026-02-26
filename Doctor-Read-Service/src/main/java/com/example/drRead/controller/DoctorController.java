package com.example.drRead.controller;

import com.example.drRead.dto.DoctorInfoDto;
import com.example.drRead.dto.DoctorTimingsInfoDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @GetMapping("/info")
    public ResponseEntity<?> getDoctorInfo(@Valid @RequestBody DoctorInfoDto doctorInfoDto){
        return null;
    }

    @GetMapping("/timings")
    public ResponseEntity<?> getDoctorTimings(@Valid @RequestBody DoctorTimingsInfoDto doctorTimingsInfoDto){
        return null;
    }


}