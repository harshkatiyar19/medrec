package medirec.demo.entity.doctor;

import jakarta.persistence.*;
import lombok.*;
import medirec.demo.entity.general.Address;
import medirec.demo.entity.general.GeneralUserDetails;

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
    //12-digit id
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="permanent_address_id",nullable = false,referencedColumnName = "address_id")
    private Address permanentAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="current_address_id",nullable = false,referencedColumnName = "address_id")
    private Address currentAddress;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private List<Qualifications> qualificationsList;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private List<DrDepartments> depatmentsList;
}


