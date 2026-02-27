package com.example.shared_library.entity.patient;

import com.example.shared_library.entity.general.Address;
import com.example.shared_library.entity.general.GeneralUserDetails;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true,
        callSuper = false)
@Table(name="patient_details")
@SequenceGenerator(
        name="seqPatientDetails",
        sequenceName = "seq_patient_details",
        allocationSize = 100
)
public class PatientUserDetails extends GeneralUserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seqPatientDetails")
    @Column(name="patient_id")
    //16-digit id
    private Long patientId;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name="permanent_address_id",
            nullable = false,
            referencedColumnName = "address_id")
    private Address permanentAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name="current_address_id",
            nullable = false,
            referencedColumnName = "address_id")
    private Address currentAddress;


    @OneToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name="emergency_contact_id",
            nullable = false,
            referencedColumnName = "emergency_contact_id")
    private EmergencyContact emergencyContact;

    @OneToMany(mappedBy = "patientUserDetails",
            fetch = FetchType.LAZY)
    private List<Reports> reportsList;

    @OneToMany(mappedBy = "patientUserDetails",
            fetch = FetchType.LAZY)
    private List<DrVisits> drVisitsList;

    @PrePersist
    public void ensure16DigitId() {
        if (patientId != null) {
            // Pad left with zeros to 12 digits
            String padded = String.format("%016d", patientId);
            this.patientId = Long.valueOf(padded);
        }
    }
}
