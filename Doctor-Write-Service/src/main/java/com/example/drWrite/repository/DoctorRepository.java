package com.example.drWrite.repository;

import com.example.shared_library.entity.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByUsername(String username);

    Optional<Doctor> findByEmail(String email);

    Optional<Doctor> findByPhno(String phno);
}
