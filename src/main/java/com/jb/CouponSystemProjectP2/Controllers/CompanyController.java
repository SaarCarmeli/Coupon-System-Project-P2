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
                companyService.createCoupon(jwtUtil.getIdFromToken(token), coupon);
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
    public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization") String token) throws CouponNotFoundException {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readAllCompanyCoupons(jwtUtil.getIdFromToken(token)));
    }

    @GetMapping("/coupon/{category}")
    public ResponseEntity<?> getCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws CouponNotFoundException {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readCompanyCouponsByCategory(jwtUtil.getIdFromToken(token), category));
    }


    @GetMapping("/coupon/{price}")
    public ResponseEntity<?> getCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double price) throws CouponNotFoundException {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readCompanyCouponsByMaxPrice(jwtUtil.getIdFromToken(token), price));
    }

    @GetMapping("/get/company-detail")
    public ResponseEntity<?> getCompanyDetail(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readCompanyDetails(jwtUtil.getIdFromToken(token)));
    }

    //-------------------------UPDATE------------------
    @PutMapping("/update-coupon")
    public ResponseEntity<?> updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponNotFoundException {
        companyService.updateCoupon(jwtUtil.getIdFromToken(token), coupon);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    // ----------------------DELETE---------------------
    @DeleteMapping("/delete-coupon/{id}")
    public ResponseEntity<?> deleteCouponById(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws CouponNotFoundException {
        companyService.deleteCouponById(couponId);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }
}
