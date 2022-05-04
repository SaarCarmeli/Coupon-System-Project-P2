package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CouponException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;

import java.util.List;

public interface CustomerServiceDAO {
    void purchaseCoupon(Coupon coupon) throws CouponException, CustomerException, CouponNotFoundException;
    List<Coupon> readAllCustomerCoupons() throws CouponNotFoundException;
    List<Coupon> readCustomerCouponsByCategory(Category category) throws CouponNotFoundException;
    List<Coupon> readCustomerCouponsByMaxPrice(double price) throws CouponNotFoundException;
    Customer readCustomerDetails();
}
