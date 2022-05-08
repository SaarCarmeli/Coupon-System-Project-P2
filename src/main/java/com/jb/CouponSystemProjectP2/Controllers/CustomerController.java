package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CouponException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    //--------------------READ------------------
    @GetMapping("/get-all-customer-coupons")
    public ResponseEntity<?> getAllCustomerCoupons() throws CouponNotFoundException {
        return new ResponseEntity<>(customerService.readAllCustomerCoupons(), HttpStatus.OK);
    }

    @GetMapping("/customer-coupons/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory(@PathVariable Category category) throws CouponNotFoundException {
        return new ResponseEntity<>(customerService.readCustomerCouponsByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/customer-coupons/{price}")
    public ResponseEntity<?> getCustomerCouponsByMaxPrice(@PathVariable double price) throws CouponNotFoundException {
        return new ResponseEntity<>(customerService.readCustomerCouponsByMaxPrice(price), HttpStatus.OK);
    }


    @GetMapping("/get/customer-detail")
    public ResponseEntity<?> getCustomerDetail() {
        return new ResponseEntity<>(customerService.readCustomerDetails(), HttpStatus.OK);
    }

    @PostMapping("/purchase-coupon")
    public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon) throws CouponException, CustomerException, CouponNotFoundException {
        customerService.purchaseCoupon(coupon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

