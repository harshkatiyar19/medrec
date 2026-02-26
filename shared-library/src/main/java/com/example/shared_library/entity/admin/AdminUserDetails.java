package com.example.shared_library.entity.admin;

import com.example.shared_library.entity.general.Address;
import com.example.shared_library.entity.general.GeneralUserDetails;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    @Column(name="admin_id")
    //12-digit id
    private Long adminId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="permanent_address_id",nullable = false,referencedColumnName = "address_id")
    private Address permanentAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="current_address_id",nullable = false,referencedColumnName = "address_id")
    private Address currentAddress;
}
