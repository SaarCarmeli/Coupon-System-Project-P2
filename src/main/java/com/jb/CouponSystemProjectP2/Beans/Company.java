package com.jb.CouponSystemProjectP2.Beans;


import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * A Company entity has a unique ID number, a name, email, password and issues Coupons which can be purchased by Customers.
 */
@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 45, nullable = false, unique = true)
    private String email;
    @Column(length = 45, nullable = false)
    private String password;
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Coupon> coupons;
}
