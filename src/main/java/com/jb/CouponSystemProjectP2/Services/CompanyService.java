package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceDAO {
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;
    private int loggedCompanyId;

    @Override
    public void createCoupon(Coupon coupon) throws CompanyException {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), this.loggedCompanyId)) {
            throw new CompanyException(
                    "Failed to create 'coupon', as 'coupon' by title= " + coupon.getTitle() + ", and by company_Id= " + this.loggedCompanyId + " already exists!");
        }
        Company loggedCompany = companyRepository.getById(loggedCompanyId);
        List<Coupon> companyCoupons = loggedCompany.getCoupons();
        companyCoupons.add(coupon);
        loggedCompany.setCoupons(companyCoupons);
        companyRepository.save(loggedCompany); // todo check if does not create duplicate coupons
    }

    @Override
    public List<Coupon> readAllCompanyCoupons() throws CompanyException {
        List<Coupon> couponList = couponRepository.findByCompanyId(this.loggedCompanyId);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CompanyException("Failed to read 'company' coupons, as 'company' did not issue any!");
    }

    @Override
    public List<Coupon> readCompanyCouponsByCategory(Category category) throws CompanyException {
        List<Coupon> couponList = couponRepository.findByCompanyIdAndCategory(this.loggedCompanyId, category);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CompanyException("Failed to read 'company' coupons, as 'company' did not issue any 'coupon' of category= " + category + "!");
    }

    @Override
    public List<Coupon> readCompanyCouponsByMaxPrice(double price) throws CompanyException {
        List<Coupon> couponList = couponRepository.findByCompanyIdAndPriceLessThan(this.loggedCompanyId, price);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CompanyException("Failed to read 'company' coupons, as 'company' did not issue any 'coupon' under price= " + price + "!");
    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCouponById(int id) {

    }

    @Override
    public Company readCompanyDetails() {
        return companyRepository.getById(this.loggedCompanyId);
    }
}
