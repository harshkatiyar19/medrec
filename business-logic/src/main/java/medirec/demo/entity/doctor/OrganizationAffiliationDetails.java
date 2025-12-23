package medirec.demo.entity.doctor;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="organization_affiliation_details")
@SequenceGenerator(
        name="seqOrganizationAffiliationDetails",
        sequenceName = "seq_organization_affiliation_details",
        allocationSize = 100
)
public class OrganizationAffiliationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqOrganizationAffiliationDetails")
    @Column(name="organization_affiliation_details_id")
    private Long organizationAffiliationDetailsId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="parent_organization_id",nullable = false,referencedColumnName = "organization_id")
    private OrganizationDetails parentOrganization;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="child_organization_id",nullable = false,referencedColumnName = "organization_id")
    private OrganizationDetails childOrganization;
}
