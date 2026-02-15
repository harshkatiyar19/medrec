package com.example.shared_library.entity.doctor;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.DayOfWeek;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="organization_timings")
@SequenceGenerator(
        name="seqOrganizationTimings",
        sequenceName = "seq_organization_timings",
        allocationSize = 100
)
public class OrganizationTimings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqOrganizationTimings")
    @Column(name="organization_timings_id")
    private Long organizationTimingsId;

    @Column(name="opening_time",nullable = false)
    private Time opneTime;

    @Column(name="closing_time",nullable = false)
    private Time closeTime;

    @Column(name="day",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="organization_details_id",nullable = false,referencedColumnName = "organization_id")
    private OrganizationDetails organizationDetails;

}
