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
    private String firstName, lastName, email, password;
    //@Singular("customers")
   // @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Coupon> coupons;
}
