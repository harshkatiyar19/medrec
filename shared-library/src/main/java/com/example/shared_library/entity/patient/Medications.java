package com.example.shared_library.entity.patient;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="medications")
@SequenceGenerator(
        name = "seqMedications",
        sequenceName = "seq_medications",
        allocationSize = 100
)
public class Medications {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "seqMedications")
    @Column(name="medications_id")
    private Long medicationId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "visit_id",referencedColumnName = "doctor_visit_id",nullable = false)
    private DrVisits drVisits;

    @CreationTimestamp
    @Column(name="start_date",nullable = false)
    private LocalDateTime startDate;

    @Column(name="end_date",nullable = false)
    private LocalDateTime endDate;

    @Column(name="medication_name",nullable = false)
    private String medicationName;

    @Column(name="dose",nullable = false)
    private String dose;

    @OneToMany(mappedBy = "medications",
            fetch = FetchType.LAZY)
    private List<DoseTime> doseTime;
}
