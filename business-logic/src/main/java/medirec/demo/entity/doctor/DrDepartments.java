package medirec.demo.entity.doctor;

import jakarta.persistence.*;
import lombok.*;
import medirec.demo.enums.JobStatus;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="dr_departments")
@SequenceGenerator(
        name="seqDrDepartments",
        sequenceName = "seq_dr_department",
        allocationSize = 100
)
public class DrDepartments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqDrDepartments")
    @Column(name="dr_department_id")
    private String drDepartmentId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "doctor_id",nullable = false,referencedColumnName = "doctor_id")
    private Doctor doctor;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "department_id",nullable = false,referencedColumnName = "department_id")
    private Departments department;

    @Column(name="designation")
    private String Designation;

    @Column(name="joining_date")
    private LocalDateTime joiningDate;

    @Column(name="leaving_date")
    private LocalDateTime leavingDate;

    @Column(name="job_status")
    @Enumerated(value = EnumType.STRING)
    private JobStatus jobStatus;

    @OneToMany(mappedBy = "drDepartments",fetch = FetchType.EAGER)
    private List<DrDepartmentsTimings> timings;
}

