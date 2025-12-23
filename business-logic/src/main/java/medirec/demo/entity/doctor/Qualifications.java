package medirec.demo.entity.doctor;

import jakarta.persistence.*;
import lombok.*;
import medirec.demo.entity.general.Address;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="qualifications")
@SequenceGenerator(
        name="seqQualifications",
        sequenceName = "seq_qualifications",
        allocationSize = 100
)
public class Qualifications {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE
            ,generator = "seqQualifications")
    @Column(name="qualification_id")
    //12-digit id
    private long qualificationId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="doctor_id",nullable = false,referencedColumnName = "doctor_id")
    private Doctor doctor;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="current_address_id",nullable = false,referencedColumnName = "address_id")
    private Address currentAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="current_address_id",nullable = false,referencedColumnName = "address_id")
    private Qualifications qualifications;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private List<DrDepartments> depatmentsList;
}
