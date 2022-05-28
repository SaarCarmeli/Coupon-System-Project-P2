package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.UserType;
import com.jb.CouponSystemProjectP2.Exceptions.*;
import com.jb.CouponSystemProjectP2.Security.JWTutil;
import com.jb.CouponSystemProjectP2.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer") // http://localhost:8080/customer
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final JWTutil jwtUtil;

    //--------------------READ------------------
    @GetMapping("/coupons/all") // http://localhost:8080/customer/coupons/all
    public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader(name = "Authorization") String token) throws CouponNotFoundException, UnauthorizedUserException, CustomerNotFoundException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)){
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customerService.readAllCustomerCoupons(jwtUtil.getIdFromToken(token)));
    }

    @GetMapping("/coupons/cat/{category}") // http://localhost:8080/customer/coupons/cat/{category}
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)){
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customerService.readCustomerCouponsByCategory(jwtUtil.getIdFromToken(token), category));
    }

    @GetMapping("/coupons/max/{price}") // http://localhost:8080/customer/coupons/max/{price}
    public ResponseEntity<?> getCustomerCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double price) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)){
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customerService.readCustomerCouponsByMaxPrice(jwtUtil.getIdFromToken(token), price));
    }


    @GetMapping("/details") // http://localhost:8080/customer/details
    public ResponseEntity<?> getCustomerDetail(@RequestHeader(name = "Authorization") String token) throws UnauthorizedUserException, CustomerNotFoundException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)){
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customerService.readCustomerDetails(jwtUtil.getIdFromToken(token)));
    }

    @PostMapping("/coupons/purchase") // http://localhost:8080/customer/coupons/purchase
    public ResponseEntity<?> purchaseCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponException, CustomerException, CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)){
            throw new UnauthorizedUserException();
        }
        customerService.purchaseCoupon(jwtUtil.getIdFromToken(token), coupon);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }
}

