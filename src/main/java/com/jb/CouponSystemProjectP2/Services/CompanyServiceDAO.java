package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;

import java.util.List;

public interface CompanyServiceDAO {
    void createCoupon(Coupon coupon);
    List<Coupon> readAllCompanyCoupons();
    List<Coupon> readCompanyCouponsByCategory(Category category);
    List<Coupon> readCompanyCouponsByMaxPrice(double price);
    void updateCoupon(Coupon coupon);
    void deleteCouponById(int id);
    Company readCompanyDetails();
}
