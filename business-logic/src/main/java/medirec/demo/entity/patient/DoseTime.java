package medirec.demo.entity.patient;

import jakarta.persistence.*;
import lombok.*;
import medirec.demo.enums.TimeSlot;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="dose_time")
@SequenceGenerator(
        name = "seqMedications",
        sequenceName = "seq_medications",
        allocationSize = 100
)
public class DoseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "seqMedications")
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "medications_id",referencedColumnName = "id",nullable = false)
    private Medications medications;

    @Column(name = "timeslot",nullable = false)
    private TimeSlot timeSlot;

    @Column(name = "remarks",nullable = false)
    private String remarks;
}
