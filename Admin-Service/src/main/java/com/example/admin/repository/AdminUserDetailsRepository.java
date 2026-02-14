package com.example.admin.repository;

import com.example.shared_library.entity.admin.AdminUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserDetailsRepository extends JpaRepository<AdminUserDetails,Long> {

}
