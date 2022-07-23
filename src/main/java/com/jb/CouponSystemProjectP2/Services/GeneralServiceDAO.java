package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Coupon;

import java.util.List;

public interface GeneralServiceDAO {
    List<Coupon> readAllCoupons();
}
