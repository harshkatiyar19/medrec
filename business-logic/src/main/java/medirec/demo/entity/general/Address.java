package medirec.demo.entity.general;

import jakarta.persistence.*;
import medirec.demo.enums.AddressType;

import java.math.BigDecimal;

@Entity
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
    private long addressId;

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

    @Column(name="address_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
}

