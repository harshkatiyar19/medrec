package com.example.spring_security.repository;

import com.example.spring_security.entity.RegisterUserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RegistraryUserRepository extends JpaRepository<RegisterUserEntity,Long> {
    RegisterUserEntity findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE RegisterUserEntity r SET r.verified = TRUE WHERE r.token = :token")
    int verifyUserByToken(String token);

    RegisterUserEntity findByToken(String token);
}
