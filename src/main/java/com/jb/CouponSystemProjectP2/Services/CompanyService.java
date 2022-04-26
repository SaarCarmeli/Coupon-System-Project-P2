package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceDAO {
    private final CompanyRepository companyRepository;
    private int loggedCompanyId;

    @Override
    public void createCoupon(Coupon coupon) {

    }

    @Override
    public List<Coupon> readAllCompanyCoupons() {
        return null;
    }

    @Override
    public List<Coupon> readCompanyCouponsByCategory(Category category) {
        return null;
    }

    @Override
    public List<Coupon> readCompanyCouponsByMaxPrice(double price) {
        return null;
    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCouponById(int id) {

    }

    @Override
    public Company readCompanyDetails() {
        return null;
    }
}
