package com.jb.CouponSystemProjectP2.Beans;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id",length = 40)
    private int id;
    private int amount;
    private double price;
    private Category category;
    private String title, description, image;
    private Date startDate, endDate;
}
