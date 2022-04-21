package com.jb.CouponSystemProjectP2.Repository;

import com.jb.CouponSystemProjectP2.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    //boolean existsByTitleAndCompany_id(String title, int company_id); todo DO NOT DELETE, fix later
    void deleteByEndDateAfter(Date endDate);

    //List<Coupon> findByCompany_id(int company_id);
    //List<Coupon> findByCompany_idAndCategory(int company_id, Category category);
    //List<Coupon> findByCompany_idAndPriceLessThan(int company_id, double price);


}
