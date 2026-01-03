package medirec.demo.entity.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medirec.demo.entity.doctor.Doctor;
import medirec.demo.entity.doctor.OrganizationDetails;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class VisitBase {
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "patient_id",referencedColumnName = "id",nullable = false)
    private PatientUserDetails patientUserDetails;

    @ManyToOne(cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="doctor_id",referencedColumnName = "doctor_id",nullable = false)
    private Doctor doctor;

    @ManyToOne(cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="organization_id",referencedColumnName = "organization_id",nullable = false)
    private OrganizationDetails organizationDetails;

    @CreationTimestamp
    @Column(name="date_created",nullable = false)
    private LocalDateTime dateCreated;
}
