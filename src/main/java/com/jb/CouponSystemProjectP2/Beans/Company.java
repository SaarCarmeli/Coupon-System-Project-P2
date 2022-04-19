package com.jb.CouponSystemProjectP2.Beans;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id",length = 40)
    private int id;
    private String name, email, password;
    @JoinColumn(name = "company_id",referencedColumnName = "company_id")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Coupon> coupons;
}
