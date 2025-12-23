package medirec.demo.entity.doctor;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="departments")
@SequenceGenerator(
        name = "seqDepartments",
        sequenceName = "seq_departments",
        allocationSize=100
)
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqDepartments")
    @Column(name="department_id")
    private String departmentId;

    @Column(name="department_name",nullable = false)
    private String departmentName;

    @Column(name="description",nullable = false)
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="organization_id",nullable = false,referencedColumnName = "organization_id")
    private OrganizationDetails organization;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private List<DrDepartments> doctorsList;
}

