package com.example.drWrite.repository;

import com.example.shared_library.entity.doctor.DrDepartments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrDepartmentsRepository extends JpaRepository<DrDepartments,Long> {
}
