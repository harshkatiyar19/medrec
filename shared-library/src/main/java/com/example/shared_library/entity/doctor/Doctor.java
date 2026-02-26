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

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="permanent_address_id",nullable = false,referencedColumnName = "address_id")
    private Address permanentAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="current_address_id",nullable = false,referencedColumnName = "address_id")
    private Address currentAddress;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private List<Qualifications> qualificationsList;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private List<DrDepartments> depatmentsList;
}


