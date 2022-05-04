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
    private int loggedCustomerId; // todo initialize

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponException, CustomerException, CouponNotFoundException {
        if (!couponRepository.exists(Example.of(coupon))) {
            throw new CouponNotFoundException("Failed to purchase 'coupon', as 'coupon' does not exist!");
        }
        if (coupon.getAmount() == 0) {
            throw new CouponException("Failed to purchase 'coupon', as no 'coupons' left in inventory!");
        }
        if (coupon.getEndDate().before(Date.from(Instant.now()))) {
            throw new CouponException("Failed to purchase 'coupon', as 'coupon' is expired!");
        }
        List<Coupon> customerCoupons = customerRepository.getById(loggedCustomerId).getCoupons();
        if (customerCoupons.stream().anyMatch(customerCoupon -> customerCoupon.equals(coupon))) {
            throw new CustomerException("Failed to purchase 'coupon', as 'coupon' is already owned by customer!");
        }
        customerCoupons.add(coupon);
        Customer customer = customerRepository.getById(this.loggedCustomerId);
        customer.setCoupons(customerCoupons);
        customerRepository.save(customer);

        coupon.setAmount(coupon.getAmount() - 1);
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public List<Coupon> readAllCustomerCoupons() throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCustomerId(this.loggedCustomerId);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'customer' coupons, as 'customer' does not own any!");
    }

    @Override
    public List<Coupon> readCustomerCouponsByCategory(Category category) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCustomerIdAndCategory(this.loggedCustomerId, category);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'customer' coupons, as 'customer' does not own any 'coupon' of category= " + category + "!");
    }

    @Override
    public List<Coupon> readCustomerCouponsByMaxPrice(double price) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCustomerIdAndPriceLessThan(this.loggedCustomerId, price);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'customer' coupons, as 'customer' does not own any 'coupon' under price= " + price + "!");
    }

    @Override
    public Customer readCustomerDetails() {
        return customerRepository.getById(this.loggedCustomerId);
    }
}
