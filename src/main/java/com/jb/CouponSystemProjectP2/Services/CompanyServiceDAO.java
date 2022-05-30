package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;

import java.sql.Date;
import java.util.List;

public interface CompanyServiceDAO {
    void createCoupon(int companyId, Coupon coupon) throws CompanyException;
    List<Coupon> readAllCompanyCoupons(int companyId) throws CouponNotFoundException;
    List<Coupon> readCompanyCouponsByCategory(int companyId, Category category) throws CouponNotFoundException;
    List<Coupon> readCompanyCouponsByMaxPrice(int companyId, double price) throws CouponNotFoundException;
    void updateCoupon(int companyId, Coupon coupon) throws CouponNotFoundException;
    void deleteCouponById(int companyId, int couponId) throws CouponNotFoundException;
    Company readCompanyDetails(int companyId);
}
