package medirec.demo.entity.general;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medirec.demo.enums.BloodGroup;
import medirec.demo.enums.Gender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class GeneralUserDetails {
    @Column(name="username",length = 100,unique = true,nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="phone_number",unique = true,nullable = false)
    private String phoneNumber;

    @Column(name="email",unique = true,nullable = false)
    private String email;

    @Column(name="image_url",nullable = false)
    private String imageUrl;

    @Column(name="date_of_birth",nullable = false)
    private LocalDate dob;

    @Column(name="date_of_death")
    private LocalDate dod;

    @Column(name="blood_group",nullable = false)
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Column(name="gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
}

