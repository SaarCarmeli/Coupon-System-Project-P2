package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;

import java.util.List;

public interface CustomerServiceDAO {
    void purchaseCoupon(Coupon coupon);
    List<Coupon> readAllCustomerCoupons();
    List<Coupon> readCustomerCouponsByCategory(Category category);
    List<Coupon> readCustomerCouponsByMaxPrice(double price);
    Customer readCustomerDetails();
}