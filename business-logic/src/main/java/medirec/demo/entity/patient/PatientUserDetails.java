package medirec.demo.entity.patient;

import jakarta.persistence.*;
import medirec.demo.entity.general.Address;
import medirec.demo.entity.general.GeneralUserDetails;

@Entity
@Table(name="patient_details")
@SequenceGenerator(
        name="seqPatientDetails",
        sequenceName = "seq_patient_details",
        allocationSize = 100
)
public class PatientUserDetails extends GeneralUserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE
            ,generator = "seqPatientDetails")
    @Column(name="id")
    //14-digit id
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="permanent_address_id",nullable = false,referencedColumnName = "address_id")
    private Address permanentAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="current_address_id",nullable = false,referencedColumnName = "address_id")
    private Address currentAddress;


    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="emergency_contact_id",nullable = false,referencedColumnName = "emergency_contact_id")
    private EmergencyContact emergencyContact;
}
