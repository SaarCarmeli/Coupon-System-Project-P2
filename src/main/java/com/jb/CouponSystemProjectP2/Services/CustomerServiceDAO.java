package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CouponException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;

import java.util.List;

public interface CustomerServiceDAO {
    void purchaseCoupon(Coupon coupon) throws CouponException, CustomerException;
    List<Coupon> readAllCustomerCoupons() throws CustomerException;
    List<Coupon> readCustomerCouponsByCategory(Category category) throws CustomerException;
    List<Coupon> readCustomerCouponsByMaxPrice(double price) throws CustomerException;
    Customer readCustomerDetails();
}
