package com.jb.CouponSystemProjectP2.Repository;

import com.jb.CouponSystemProjectP2.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    void deleteByEndDateAfter(Date endDate);
}
