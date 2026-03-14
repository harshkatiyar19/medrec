package com.example.shared_library.entity.general;

import com.example.shared_library.enums.BloodGroup;
import com.example.shared_library.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class GeneralUserDetails {
//    @Column(name="username",length = 100,unique = true,nullable = false)
//    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="phone_number",unique = true,nullable = false)
    private String phoneNumber;

    @Column(name="email",unique = true,nullable = false)
    private String email;

//    @Column(name="image_url",nullable = false)
//    private String imageUrl;

    @Column(name="date_of_birth",nullable = false)
    private LocalDate dob;

    @Column(name="date_of_death")
    private LocalDate dod;

    @Column(name="blood_group",nullable = false,length = 3)
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Column(name="gender",nullable = false,length=1)
    private Gender gender;
}

