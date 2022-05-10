package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.TokenException;
import com.jb.CouponSystemProjectP2.Security.JWTutil;
import com.jb.CouponSystemProjectP2.Services.CompanyService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final JWTutil jwtUtil;

    // -------------------CREATE--------------------
    @PostMapping("/add/coupon")
    public ResponseEntity<?> createNewCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CompanyException, TokenException {
        try {
            if (jwtUtil.isTokenValid(token)) {
                companyService.createCoupon(coupon);
                return ResponseEntity.accepted()
                        .header("Authorization", jwtUtil.generateToken(token))
                        .build();
            } else {
                throw new TokenException();//todo custom error
            }
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException e) {
            throw new TokenException(e.getMessage());
        }
    }

    //---------------READ----------------------
    @GetMapping("/get-all-coupons")
    public ResponseEntity<?> getAllCoupons() throws CouponNotFoundException {
        return new ResponseEntity<>(companyService.readAllCompanyCoupons(), HttpStatus.OK);
    }

    @GetMapping("/get/company-detail")
    public ResponseEntity<?> getCompanyDetail() {
        return new ResponseEntity<>(companyService.readCompanyDetails(), HttpStatus.OK);
    }

    @GetMapping("/coupon/{category}")
    public ResponseEntity<?> getCouponsByCategory(@PathVariable Category category) throws CouponNotFoundException {
        return new ResponseEntity<>(companyService.readCompanyCouponsByCategory(category), HttpStatus.OK);
    }


    @GetMapping("/coupon/{price}")
    public ResponseEntity<?> getCouponsByMaxPrice(@PathVariable double price) throws CouponNotFoundException {
        return new ResponseEntity<>(companyService.readCompanyCouponsByMaxPrice(price), HttpStatus.OK);
    }

    //-------------------------UPDATE------------------
    @PutMapping("/update-coupon")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCoupon(@RequestBody Coupon coupon) throws CouponNotFoundException {
        companyService.updateCoupon(coupon);
    }

    // ----------------------DELETE---------------------
    @DeleteMapping("/delete-coupon/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCouponById(@PathVariable int id) throws CouponNotFoundException {
        companyService.deleteCouponById(id);
    }
}
