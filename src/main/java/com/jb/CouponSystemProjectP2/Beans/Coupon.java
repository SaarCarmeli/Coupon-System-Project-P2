package com.jb.CouponSystemProjectP2.Beans;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

/**
 * A Coupon entity has a unique ID number, is issued by a Company and can be purchased by Customers.
 * A Coupon has a price, category (enum), title, description and image which define it.
 * An amount number determines how many can be purchased.
 * A Coupon's start date is the date it is created on
 * A Coupon's end date is its expiration date.
 */
@Entity
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private int id;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private double price;
    @Enumerated(EnumType.STRING)
    @Column(length = 45, nullable = false)
    private Category category;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    private String description;
    @Column(length = 45, nullable = false)
    private String image;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
}
