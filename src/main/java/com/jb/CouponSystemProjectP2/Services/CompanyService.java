package com.jb.CouponSystemProjectP2.Services;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * A service class implementing all the methods available for Company user.
 */
@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceDAO {
    @Autowired
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;

    /**
     * A method to create a new Coupon entity for a Company
     *
     * @param companyId ID of the Company the Coupon belongs to
     * @param coupon    New Coupon created for the Company
     * @throws CompanyException Thrown if the Coupon already exists in the Company under the new Coupon's title
     */
    @Override
    public void createCoupon(int companyId, Coupon coupon) throws CompanyException {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), companyId) == 1) {
            throw new CompanyException(
                    "Failed to create 'coupon', as 'coupon' by title= " + coupon.getTitle() + ", and by company_Id= " + companyId + " already exists!");
        }
        Company loggedCompany = companyRepository.getById(companyId);
        List<Coupon> companyCoupons = loggedCompany.getCoupons();
        companyCoupons.add(coupon);
        loggedCompany.setCoupons(companyCoupons);
        companyRepository.save(loggedCompany); // todo check if does not create duplicate coupons
    }

    /**
     * A method that returns all the Company's Coupons.
     *
     * @param companyId ID number of the Company
     * @return List of all the Company's Coupons
     * @throws CouponNotFoundException Thrown if the List is empty (Company has no Coupons)
     */
    @Override
    public List<Coupon> readAllCompanyCoupons(int companyId) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCompanyId(companyId);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'company' coupons, as 'company' did not issue any!");
    }

    /**
     * A method that returns Company's Coupons of a certain Category.
     *
     * @param companyId ID number of the Company
     * @param category  Coupon's Category
     * @return List of Company's Coupons of argument Category
     * @throws CouponNotFoundException Thrown if the List is empty (Company has no Coupons in that Category)
     */
    @Override
    public List<Coupon> readCompanyCouponsByCategory(int companyId, Category category) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCompanyIdAndCategory(companyId, category.name());
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'company' coupons, as 'company' did not issue any 'coupon' of category= " + category + "!");
    }

    /**
     * A method that returns Company's Coupons under a certain price.
     *
     * @param companyId ID number of the Company
     * @param price     Maximum price
     * @return List of Company's Coupons under argument price
     * @throws CouponNotFoundException Thrown if the List is empty (Company has no Coupons under the max price)
     */
    @Override
    public List<Coupon> readCompanyCouponsByMaxPrice(int companyId, double price) throws CouponNotFoundException {
        List<Coupon> couponList = couponRepository.findByCompanyIdAndPriceLessThan(companyId, price);
        if (!couponList.isEmpty()) {
            return couponList;
        }
        throw new CouponNotFoundException("Failed to read 'company' coupons, as 'company' did not issue any 'coupon' under price= " + price + "!");
    }

    /**
     * A method to update a Company's Coupon.
     *
     * @param companyId ID number of the Company
     * @param coupon    Coupon entity for update
     * @throws CouponNotFoundException Thrown if the Coupon ID number is not found with the corresponding Company ID number
     */
    @Override
    public void updateCoupon(int companyId, Coupon coupon) throws CouponNotFoundException {
        if (couponRepository.existsByCouponIdAndCompanyId(coupon.getId(), companyId) == 1) {
            couponRepository.save(coupon);
        } else {
            throw new CouponNotFoundException("Failed to update 'coupon', as 'coupon' by ID= " + coupon.getId() + ", and by company_Id= " + companyId + " does not exist!");
        }
    }

    /**
     * A method to delete a Company's Coupon.
     *
     * @param companyId ID number of the Company
     * @param couponId  ID number of the Coupon to be deleted
     * @throws CouponNotFoundException Thrown if the Coupon ID number is not found with the corresponding Company ID number
     */
    @Override
    public void deleteCouponById(int companyId, int couponId) throws CouponNotFoundException {
        if (couponRepository.existsByCouponIdAndCompanyId(couponId, companyId) == 1) {
            couponRepository.deleteById(couponId);
        } else {
            throw new CouponNotFoundException("Failed to delete 'coupon', as 'coupon' by ID= " + couponId + ", and by company_Id= " + companyId + " does not exist!");
        }
    }

    /**
     * A method that returns the details of the Company of a given ID number
     *
     * @param companyId ID number of the Company
     * @return Company entity
     */
    @Override
    public Company readCompanyDetails(int companyId) throws CompanyNotFoundException {
        if (companyRepository.existsById(companyId)) {
            return companyRepository.getById(companyId);
        } else {
            throw new CompanyNotFoundException("Failed to read 'company' details , as 'company' by ID= " + companyId + " does not exist!");
        }
    }
}
