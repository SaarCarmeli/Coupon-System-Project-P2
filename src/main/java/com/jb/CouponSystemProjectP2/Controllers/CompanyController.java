package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
// -------------------CREATE--------------------


    @PostMapping("/add/coupon")
    public ResponseEntity<?> createNewCoupon(@RequestBody Coupon coupon) {//todo:throw exception
        companyService.createCoupon(coupon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //---------------READ----------------------
    @GetMapping("/get-all-coupons")
    public ResponseEntity<?> getAllCoupons() { //todo:throw exception
        return new ResponseEntity<>(companyService.readAllCompanyCoupons(), HttpStatus.OK);
    }

    @GetMapping("/get/company-detail")
    public ResponseEntity<?> getCompanyDetail() { //todo:throw exception
        return new ResponseEntity<>(companyService.readCompanyDetails(), HttpStatus.OK);
    }

    @GetMapping("/coupon/{category}")
    public ResponseEntity<?> getCouponsByCategory(@PathVariable Category category) {//todo:throw exception
        return new ResponseEntity<>(companyService.readCompanyCouponsByCategory(category), HttpStatus.ACCEPTED);
    }


    @GetMapping("/coupon/{price}")
    public ResponseEntity<?> getCouponsByMaxPrice(@PathVariable double price) {//todo:throw exception
        return new ResponseEntity<>(companyService.readCompanyCouponsByMaxPrice(price), HttpStatus.ACCEPTED);
    }

    //-------------------------UPDATE------------------
    @PutMapping("/update-coupon")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCoupon(@RequestBody Coupon coupon) {//todo:throw exception
        companyService.updateCoupon(coupon);
    }

    // ----------------------DELETE---------------------
    @DeleteMapping("/delete-coupon/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCouponById(@PathVariable int id) {//todo:throw exception
        companyService.deleteCouponById(id);
    }
}
