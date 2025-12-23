package medirec.demo.entity.doctor;

import jakarta.persistence.*;

import lombok.*;
import medirec.demo.entity.general.Address;
import medirec.demo.enums.OperationalStatus;
import medirec.demo.enums.OrganizationType;
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
    private String organizationId;

    @Column(name="name",nullable = false)
    private String name ;

    @Column(name="organization_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

    @Column(name="license_number",nullable = false,unique = true)
    private String licenseNumber;

    @Column(name="registrationAuthority",nullable = false)
    private String registrationAuthority;

    @Column(name="licenseExpiryDate",nullable = false)
    private LocalDate licenseExpiryDate;

    @Column(name="phoneNumber",nullable = false,unique = true)
    private String phoneNumber;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="websiteUrl",nullable = false)
    private String websiteUrl;

    @Column(name="operational_status",nullable = false)
    private OperationalStatus operationalStatus;

    @Column(name="formed_at",nullable = false)
    private LocalDate formedAt;

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
