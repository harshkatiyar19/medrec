package com.example.shared_library.entity.doctor;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.DayOfWeek;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="dr_departments_timings")
@SequenceGenerator(
        name="seqDrDepartmentsTimings",
        sequenceName = "seq_dr_department_timings",
        allocationSize = 100
)
public class DrDepartmentsTimings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqDrDepartmentsTimings")
    @Column(name="dr_department_id")
    private Long drDepartmentTimingsId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "doctor_department_id",nullable = false,referencedColumnName = "dr_department_id")
    private DrDepartments drDepartments;

    @Column(name="day",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name="start_time",nullable = false)
    private Time startTime;

    @Column(name="end_time",nullable = false)
    private Time endTime;
}
