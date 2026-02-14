package com.example.shared_library.entity.patient;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "dr_visits")
@SequenceGenerator(name = "seqDrVisits",
        sequenceName ="seq_dr_visits" ,
        allocationSize = 100)
public class DrVisits extends VisitBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="seqDrVisits")
    @Column(name="doctor_visit_id")
    private Long doctorVisitId;

    @Column(name ="symptoms",nullable = false)
    private String symptoms;

    @Column(name ="diagnosis",nullable = false)
    private String diagnosis;

    @Column(name ="remarks",nullable = false)
    private String remarks;

    @Column(name ="tests",nullable = false)
    private String tests;

    @OneToMany(mappedBy = "drVisits",
            fetch = FetchType.LAZY)
    private List<Medications> meds;
}
