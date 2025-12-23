package medirec.demo.entity.admin;

import jakarta.persistence.*;
import medirec.demo.entity.general.Address;
import medirec.demo.entity.general.GeneralUserDetails;

@Entity
@Table(name="admin_details")
@SequenceGenerator(
        name="seqAdminDetails",
        sequenceName = "seq_admin_details",
        allocationSize = 100
)
public class AdminUserDetails extends GeneralUserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE
            ,generator = "seqAdminDetails")
    @Column(name="id")
    //10-digit id
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="permanent_address_id",nullable = false,referencedColumnName = "address_id")
    private Address permanentAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="current_address_id",nullable = false,referencedColumnName = "address_id")
    private Address currentAddress;
}
