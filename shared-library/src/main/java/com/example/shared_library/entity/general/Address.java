package com.example.shared_library.entity.general;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="address")
@SequenceGenerator(
        name="seqAddress",
        sequenceName = "seq_address",
        allocationSize = 100
)
public class Address{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seqAddress"
    )
    @Column(name="address_id")
    private Long addressId;

    @Column(name="address_line_1",nullable = false)
    private String addressLine1;

    @Column(name="address_line_2",nullable = false)
    private String addressLine2;

    @Column(name="city",nullable = false)
    private String city;

    @Column(name="district",nullable = false)
    private String district;

    @Column(name="state",nullable = false)
    private String state;

    @Column(name="country",nullable = false)
    private String country;

    @Column(name="pin_code",length = 10,nullable = false)
    private String pinCode;

    @Column(name="latitude",precision = 9,scale = 6,nullable = false)
    private BigDecimal latitude;

    @Column(name="longitude",precision = 9,scale = 6,nullable = false)
    private BigDecimal longitude;

    @Column(name="map_url",nullable = false)
    private String mapUrl;

}

