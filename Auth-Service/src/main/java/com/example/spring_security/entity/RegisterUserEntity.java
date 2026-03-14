package com.example.spring_security.entity;

import com.example.spring_security.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_register")
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true,
        callSuper = false)
@SequenceGenerator(
        name="seqUserRegister",
        sequenceName = "seq_user_register",
        allocationSize = 100
)
public class RegisterUserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seqUserRegister")
    @Column(name="registration_id")
    private Long registrationId;

    private String email;

    private String password;

    private String token;

    private Boolean verified;

    private LocalDateTime tokenMade;

    private LocalDateTime tokenValidTill;

    private TypeUser typeUser;

    @PrePersist
    public void init() {
        if (verified != null) {
            // Pad left with zeros to 12 digits
            this.verified = Boolean.FALSE;
        }
    }
}
