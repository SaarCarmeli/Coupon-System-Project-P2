package com.jb.CouponSystemProjectP2.Service;

import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;


    public void createCoupon(Coupon coupon) {
        couponRepository.save(coupon);
    }

    public Coupon readCouponById(int id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        return coupon.orElse(null);
    }

    public List<Coupon> readAllCoupons() {
        return couponRepository.findAll();
    }

    public void updateCoupon(Coupon coupon) {
        if (couponRepository.existsById(coupon.getId())) {
            couponRepository.save(coupon);
        } else {
            System.out.println("Coupon not found");  // todo: customize exception.
        }
    }

    public void deleteCoupon(int id) {
        couponRepository.deleteById(id);
    }

    public boolean isCouponExists(int id) {
        return couponRepository.existsById(id);
    }
}