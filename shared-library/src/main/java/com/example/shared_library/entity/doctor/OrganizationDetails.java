package com.example.shared_library.entity.doctor;

import com.example.shared_library.entity.general.Address;
import com.example.shared_library.enums.doctor.OperationalStatus;
import com.example.shared_library.enums.doctor.OrganizationType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="organization_details")
@SequenceGenerator(
        name="seqOrganizationDetails",
        sequenceName = "seq_organization_details",
        allocationSize = 100
)
public class OrganizationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqOrganizationDetails")
    @Column(name="organization_id")
    private Long organizationId;

    @Column(name="name",nullable = false)
    private String name ;

    @Column(name="license_number",nullable = false,unique = true)
    private String licenseNumber;

    @Column(name="registration_authority",nullable = false)
    private String registrationAuthority;

    @Column(name="phone_number",nullable = false,unique = true)
    private String phoneNumber;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="website_url",nullable = false)
    private String websiteUrl;

    @Column(name="license_expiry_date",nullable = false)
    private LocalDate licenseExpiryDate;

    @Column(name="formed_at",nullable = false)
    private LocalDate formedAt;

    @Column(name="operational_status",nullable = false)
    @Enumerated
    private OperationalStatus operationalStatus;

    @Column(name="organization_type",nullable = false,length=1)
    private OrganizationType organizationType;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="address_id",nullable = false,referencedColumnName = "address_id")
    private Address address;

    @OneToMany(mappedBy = "parentOrganization", fetch = FetchType.LAZY)
    private List<OrganizationAffiliationDetails> childAffiliations;

    @OneToMany(mappedBy = "childOrganization", fetch = FetchType.LAZY)
    private List<OrganizationAffiliationDetails> parentAffiliations;

    @OneToMany(mappedBy = "organizationDetails",fetch = FetchType.EAGER)
    private List<OrganizationTimings> timings;

    @OneToMany(mappedBy = "organizationDetails",fetch = FetchType.LAZY)
    private List<Departments> departmentsList;
}
