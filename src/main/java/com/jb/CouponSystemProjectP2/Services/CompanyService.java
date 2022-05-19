package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
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

    @Override
    public void createCoupon(int companyId, Coupon coupon) throws CompanyException {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
            throw new CompanyException(
                    "Failed to create 'coupon', as 'coupon' by title= " + coupon.getTitle() + ", and by company_Id= " + companyId + " already exists!");
        }
        Company loggedCompany = companyRepository.getById(companyId);
        List<Coupon> companyCoupons = loggedCompany.getCoupons();
        companyCoupons.add(coupon);
        loggedCompany.setCoupons(companyCoupons);
        companyRepository.save(loggedCompany); // todo check if does not create duplicate coupons
    }

    @Override
    public List<Coupon> readAllCompanyCoupons(int companyId) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCompanyId(companyId);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'company' coupons, as 'company' did not issue any!");
    }

    @Override
    public List<Coupon> readCompanyCouponsByCategory(int companyId, Category category) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCompanyIdAndCategory(companyId, category.name());
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'company' coupons, as 'company' did not issue any 'coupon' of category= " + category + "!");
    }

    @Override
    public List<Coupon> readCompanyCouponsByMaxPrice(int companyId, double price) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCompanyIdAndPriceLessThan(companyId, price);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'company' coupons, as 'company' did not issue any 'coupon' under price= " + price + "!");
    }

    @Override
    public void updateCoupon(int id, Coupon coupon) throws CouponNotFoundException {
        // todo restrict update to coupon.company_id and coupon.id
        // todo check that coupon belongs to the company that logged in
        if (couponRepository.existsById(coupon.getId())) {
            couponRepository.save(coupon);
        } else {
            throw new CouponNotFoundException("Failed to update 'coupon', as 'coupon' by ID= " + coupon.getId() + " does not exist!");
        }
    }

    @Override
    public void deleteCouponById(int couponId) throws CouponNotFoundException {
        // todo check that coupon belongs to logged-in company
        if (couponRepository.existsById(couponId)) {
            couponRepository.deleteById(couponId);
        } else {
            throw new CouponNotFoundException("Failed to delete 'coupon', as 'coupon' by ID= " + couponId + " does not exist!");
        }
    }

    @Override
    public Company readCompanyDetails(int companyId) {
        return companyRepository.getById(companyId);
    }
}
