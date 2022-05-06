package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;

import java.util.List;

public interface CompanyServiceDAO {
    void createCoupon(Coupon coupon) throws CompanyException;
    List<Coupon> readAllCompanyCoupons() throws CouponNotFoundException;
    List<Coupon> readCompanyCouponsByCategory(Category category) throws CouponNotFoundException;
    List<Coupon> readCompanyCouponsByMaxPrice(double price) throws CouponNotFoundException;
    void updateCoupon(Coupon coupon) throws CouponNotFoundException;
    void deleteCouponById(int id) throws CouponNotFoundException;
    Company readCompanyDetails();
}
