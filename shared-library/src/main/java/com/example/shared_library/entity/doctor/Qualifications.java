package com.example.shared_library.entity.doctor;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="qualifications")
@SequenceGenerator(
        name="seqQualifications",
        sequenceName = "seq_qualifications",
        allocationSize = 100
)
public class Qualifications {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE
            ,generator = "seqQualifications")
    @Column(name="qualification_id")
    //12-digit id
    private long qualificationId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="doctor_id",nullable = false,referencedColumnName = "doctor_id")
    private Doctor doctor;

    @Column(name="qualification_name")
    private String name;

    @Column(name="proof")
    private String proof;
}
