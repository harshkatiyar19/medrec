package com.example.shared_library.entity.patient;

import com.example.shared_library.enums.patient.TimeSlot;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="dose_time")
@SequenceGenerator(
        name = "seqDoseTime",
        sequenceName = "seq_dose_time",
        allocationSize = 100
)
public class DoseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "seqMedications")
    @Column(name="dose_time_id")
    private Long doseTimeId;

    @Column(name = "remarks",nullable = false)
    private String remarks;

    @Column(name = "timeslot",nullable = false,length = 1)
    private TimeSlot timeSlot;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "medications_id",referencedColumnName = "medications_id",nullable = false)
    private Medications medications;
}
