package com.example.drWrite.repository;

import com.example.shared_library.entity.doctor.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepository extends JpaRepository<Departments,Long> {
}
