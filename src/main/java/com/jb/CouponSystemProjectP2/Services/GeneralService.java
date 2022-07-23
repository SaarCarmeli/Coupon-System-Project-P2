package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralService implements GeneralServiceDAO {
    private final CouponRepository couponRepository;

    @Override
    public List<Coupon> readAllCoupons() {
        return couponRepository.findAll();
    }
}
