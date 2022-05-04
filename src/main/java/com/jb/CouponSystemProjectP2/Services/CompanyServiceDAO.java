package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;

import java.util.List;

public interface CompanyServiceDAO {
    void createCoupon(Coupon coupon) throws CompanyException;
    List<Coupon> readAllCompanyCoupons() throws CompanyException;
    List<Coupon> readCompanyCouponsByCategory(Category category) throws CompanyException;
    List<Coupon> readCompanyCouponsByMaxPrice(double price) throws CompanyException;
    void updateCoupon(Coupon coupon);
    void deleteCouponById(int id);
    Company readCompanyDetails();
}
