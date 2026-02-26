package com.example.admin.repository;

import com.example.shared_library.entity.admin.AdminUserDetails;
import com.example.shared_library.entity.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserDetailsRepository extends JpaRepository<AdminUserDetails,Long> {
    Optional<AdminUserDetails> findByAdminId(Long adminId);

    Optional<AdminUserDetails> findByEmail(String email);

    Optional<AdminUserDetails> findByPhoneNumber(String phoneNumber);
}
