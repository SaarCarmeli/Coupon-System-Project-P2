package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CouponException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Repositories.CouponRepository;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceDAO {
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    @Override
    public void purchaseCoupon(int customerId, Coupon coupon) throws CouponException, CustomerException, CouponNotFoundException {
        if (!couponRepository.exists(Example.of(coupon))) {
            throw new CouponNotFoundException("Failed to purchase 'coupon', as 'coupon' does not exist!");
        }
        if (coupon.getAmount() == 0) {
            throw new CouponException("Failed to purchase 'coupon', as no 'coupons' left in inventory!");
        }
        if (coupon.getEndDate().before(Date.from(Instant.now()))) {
            throw new CouponException("Failed to purchase 'coupon', as 'coupon' is expired!");
        }
        List<Coupon> customerCoupons = customerRepository.getById(customerId).getCoupons();
        if (customerCoupons.stream().anyMatch(customerCoupon -> customerCoupon.equals(coupon))) {
            throw new CustomerException("Failed to purchase 'coupon', as 'coupon' is already owned by customer!");
        }
        customerCoupons.add(coupon);
        Customer customer = customerRepository.getById(customerId);
        customer.setCoupons(customerCoupons);
        customerRepository.save(customer);

        coupon.setAmount(coupon.getAmount() - 1);
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public List<Coupon> readAllCustomerCoupons(int customerId) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCustomerId(customerId);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'customer' coupons, as 'customer' does not own any!");
    }

    @Override
    public List<Coupon> readCustomerCouponsByCategory(int customerId, Category category) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCustomerIdAndCategory(customerId, category);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'customer' coupons, as 'customer' does not own any 'coupon' of category= " + category + "!");
    }

    @Override
    public List<Coupon> readCustomerCouponsByMaxPrice(int customerId, double price) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCustomerIdAndPriceLessThan(customerId, price);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'customer' coupons, as 'customer' does not own any 'coupon' under price= " + price + "!");
    }

    @Override
    public Customer readCustomerDetails(int customerId) {
        return customerRepository.getById(customerId);
    }
}
