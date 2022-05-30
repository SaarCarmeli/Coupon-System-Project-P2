package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CouponException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceDAO {
    void purchaseCoupon(int customerId, Coupon coupon) throws CouponException, CustomerException, CouponNotFoundException;
    List<Coupon> readAllCustomerCoupons(int customerId) throws CouponNotFoundException, CustomerNotFoundException;
    List<Coupon> readCustomerCouponsByCategory(int customerId, Category category) throws CouponNotFoundException;
    List<Coupon> readCustomerCouponsByMaxPrice(int customerId, double price) throws CouponNotFoundException;
    Customer readCustomerDetails(int customerId) throws CustomerNotFoundException;
}
