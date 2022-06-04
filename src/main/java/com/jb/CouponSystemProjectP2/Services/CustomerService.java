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

/**
 * A service class implementing all the methods available for Customer user.
 */
@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceDAO {
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    /**
     * A method to add a new Coupon purchase to a Customer.
     *
     * @param customerId ID number of the purchasing Customer
     * @param coupon     Coupon being purchased
     * @throws CouponException         Thrown if Coupon is expired or no Coupons are left to buy
     * @throws CustomerException       Thrown if Customer already owns the Coupon
     * @throws CouponNotFoundException Thrown if Coupon does not exist
     */
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

    /**
     * A method that returns all the Customer's Coupons.
     *
     * @param customerId ID number of the Customer
     * @return List of all the Customer's Coupons
     * @throws CouponNotFoundException Thrown if the List is empty (Customer has no Coupons)
     */
    @Override
    public List<Coupon> readAllCustomerCoupons(int customerId) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCustomerId(customerId);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'customer' coupons, as 'customer' does not own any!");
    }

    /**
     * A method that returns Customer's Coupons of a certain Category.
     *
     * @param customerId ID number of the Customer
     * @param category   Coupon's Category
     * @return List of Customer's Coupons of argument Category
     * @throws CouponNotFoundException Thrown if the List is empty (Customer has no Coupons in that Category)
     */
    @Override
    public List<Coupon> readCustomerCouponsByCategory(int customerId, Category category) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCustomerIdAndCategory(customerId, category.name());
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'customer' coupons, as 'customer' does not own any 'coupon' of category= " + category + "!");
    }

    /**
     * A method that returns Customer's Coupons under a certain price.
     *
     * @param customerId ID number of the Customer
     * @param price      Maximum price
     * @return List of Customer's Coupons under argument price
     * @throws CouponNotFoundException Thrown if the List is empty (Customer has no Coupons under the max price)
     */
    @Override
    public List<Coupon> readCustomerCouponsByMaxPrice(int customerId, double price) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCustomerIdAndPriceLessThan(customerId, price);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'customer' coupons, as 'customer' does not own any 'coupon' under price= " + price + "!");
    }

    /**
     * A method that returns the details of the Customer of a given ID number
     *
     * @param customerId ID number of the Customer
     * @return Customer entity
     */
    @Override
    public Customer readCustomerDetails(int customerId) {
        return customerRepository.getById(customerId);
    }
}
