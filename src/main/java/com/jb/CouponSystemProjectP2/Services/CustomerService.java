package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceDAO {
    private final CustomerRepository customerRepository;
    private int loggedCustomerId; // todo initialize

    @Override
    public void purchaseCoupon(Coupon coupon) {

    }

    @Override
    public List<Coupon> readAllCustomerCoupons() {
        return null;
    }

    @Override
    public List<Coupon> readCustomerCouponsByCategory(Category category) {
        return null;
    }

    @Override
    public List<Coupon> readCustomerCouponsByMaxPrice(double price) {
        return null;
    }

    @Override
    public Customer readCustomerDetails() {
        return null;
    }
}
