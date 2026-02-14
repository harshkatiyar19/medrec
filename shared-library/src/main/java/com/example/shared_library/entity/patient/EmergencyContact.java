package com.example.shared_library.entity.patient;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="emergency_contacts")
@SequenceGenerator(
        name="seqEmergencyContacts",
        sequenceName = "seq_emergency_contacts",
        allocationSize = 100
)
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqEmergencyContacts")
    @Column(name = "emergency_contact_id")
    private long emergencyId;
    @Column(name="phone_number",nullable = false)
    private String phoneNumber;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="name",nullable = false)
    private String name;
}
