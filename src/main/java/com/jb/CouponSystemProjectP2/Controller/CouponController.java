package com.jb.CouponSystemProjectP2.Controller;

import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<>(couponService.readAllCoupons(), HttpStatus.OK);
    }

    @GetMapping("/coupons/{id}")
    public ResponseEntity<?> getCouponById(@PathVariable int id) {
        return new ResponseEntity<>(couponService.readCouponById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNewCoupon(@RequestBody Coupon coupon) {
        couponService.createCoupon(coupon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCoupon(@RequestBody Coupon coupon) {
        try {
            couponService.updateCoupon(coupon);
        } catch (Exception e) {
            System.out.println("Failed to update coupon"); // todo: customize exception.
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCoupon(@PathVariable int id) throws Exception {
        try {
            couponService.deleteCoupon(id);
        } catch (Exception e) {
            System.out.println("Failed to delete coupon"); //todo:customize exception
        }
    }
}
