package com.example.shared_library.entity.doctor;

import com.example.shared_library.entity.general.Address;
import com.example.shared_library.entity.general.GeneralUserDetails;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="doctor_details")
@SequenceGenerator(
        name="seqDoctorDetails",
        sequenceName = "seq_doctor_details",
        allocationSize = 100
)
public class Doctor extends GeneralUserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE
            ,generator = "seqDoctorDetails")
    @Column(name="doctor_id")
    //14-digit id
    private Long doctorId;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name="address_id",
            nullable = false,
            referencedColumnName = "address_id")
    private Address address;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private List<Qualifications> qualificationsList;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private List<DrDepartments> depatmentsList;

    @PrePersist
    public void ensure14DigitId() {
        if (doctorId != null) {
            // Pad left with zeros to 12 digits
            String padded = String.format("%014d", doctorId);
            this.doctorId = Long.valueOf(padded);
        }
    }
}


