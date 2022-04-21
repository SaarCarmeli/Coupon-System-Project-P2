package com.jb.CouponSystemProjectP2.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",length = 40)
    private int id;
    @Column(length = 40, nullable = false)
    private String firstName;
    @Column(length = 40, nullable = false)
    private String lastName;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(length = 40, nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Coupon> coupons;
}
