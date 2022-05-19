package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.UserType;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.TokenException;
import com.jb.CouponSystemProjectP2.Exceptions.UnauthorizedUserException;
import com.jb.CouponSystemProjectP2.Security.JWTutil;
import com.jb.CouponSystemProjectP2.Services.CompanyService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/company") // http://localhost:8080/company
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final JWTutil jwtUtil;

    // -------------------CREATE--------------------
    @PostMapping("/coupons/add") // http://localhost:8080/company/coupons/add
    public ResponseEntity<?> createNewCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CompanyException, TokenException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)){
            throw new UnauthorizedUserException();
        }
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
    @GetMapping("/coupons/all") // http://localhost:8080/company/coupons/all
    public ResponseEntity<?> getAllCompanyCoupons(@RequestHeader(name = "Authorization") String token) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)){
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readAllCompanyCoupons(jwtUtil.getIdFromToken(token)));
    }

    @GetMapping("/coupons/cat/{category}") // http://localhost:8080/company/coupons/cat/{category}
    public ResponseEntity<?> getCompanyCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)){
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readCompanyCouponsByCategory(jwtUtil.getIdFromToken(token), category));
    }


    @GetMapping("/coupons/max/{price}") // http://localhost:8080/company/coupons/max/{price}
    public ResponseEntity<?> getCompanyCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double price) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)){
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readCompanyCouponsByMaxPrice(jwtUtil.getIdFromToken(token), price));
    }

    @GetMapping("/details") // http://localhost:8080/company/details
    public ResponseEntity<?> getCompanyDetail(@RequestHeader(name = "Authorization") String token) throws UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)){
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readCompanyDetails(jwtUtil.getIdFromToken(token)));
    }

    //-------------------------UPDATE------------------
    @PutMapping("/coupons/update") // http://localhost:8080/company/coupons/update
    public ResponseEntity<?> updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)){
            throw new UnauthorizedUserException();
        }
        companyService.updateCoupon(jwtUtil.getIdFromToken(token), coupon);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    // ----------------------DELETE---------------------
    @DeleteMapping("/coupons/delete/{id}") // http://localhost:8080/company/coupons/delete/{id}
    public ResponseEntity<?> deleteCouponById(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)){
            throw new UnauthorizedUserException();
        }
        companyService.deleteCouponById(couponId);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }
}
