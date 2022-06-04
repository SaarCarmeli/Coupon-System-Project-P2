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

/**
 * RESTful web service class (RestController) for Customer client type.
 */
@RestController
@RequestMapping("/customer") // http://localhost:8080/customer
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final JWTutil jwtUtil;

    /**
     * A method to add a new Coupon purchase to the Customer client.
     *
     * @param token  Valid, logged Customer's JSON Web Token (JWT)
     * @param coupon Coupon being purchased by client
     * @return New token and 202-Accepted HTTP status
     * @throws CouponException           Thrown if Coupon is expired or no Coupons are left to buy
     * @throws CustomerException         Thrown if Customer already owns the Coupon
     * @throws CouponNotFoundException   Thrown if Coupon does not exist
     * @throws UnauthorizedUserException Thrown if a client type other than Customer attempts to use this method
     */
    @PostMapping("/coupons/purchase") // http://localhost:8080/customer/coupons/purchase
    public ResponseEntity<?> purchaseCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponException, CustomerException, CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)) {
            throw new UnauthorizedUserException();
        }
        customerService.purchaseCoupon(jwtUtil.getIdFromToken(token), coupon);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    /**
     * A method that returns all the Customer's Coupons to the client.
     *
     * @param token Valid, logged Customer's JSON Web Token (JWT)
     * @return New token, 200-Ok HTTP status and List of all Customer's Coupons
     * @throws CouponNotFoundException   Thrown if Customer has no Coupons
     * @throws UnauthorizedUserException Thrown if a client type other than Customer attempts to use this method
     */
    @GetMapping("/coupons/all") // http://localhost:8080/customer/coupons/all
    public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader(name = "Authorization") String token) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customerService.readAllCustomerCoupons(jwtUtil.getIdFromToken(token)));
    }

    /**
     * A method that returns Customer's Coupons of a certain Category to the client.
     *
     * @param token    Valid, logged Customer's JSON Web Token (JWT)
     * @param category Coupon Category to select by
     * @return New token, 200-Ok HTTP status and List of Customer's Coupons by given Category
     * @throws CouponNotFoundException   Thrown if the List is empty (Customer has no Coupons in that Category)
     * @throws UnauthorizedUserException Thrown if a client type other than Customer attempts to use this method
     */
    @GetMapping("/coupons/cat/{category}") // http://localhost:8080/customer/coupons/cat/{category}
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customerService.readCustomerCouponsByCategory(jwtUtil.getIdFromToken(token), category));
    }

    /**
     * A method that returns Customer's Coupons under a certain price to the client.
     *
     * @param token Valid, logged Customer's JSON Web Token (JWT)
     * @param price Maximum price
     * @return New token, 200-Ok HTTP status and List of Customer's Coupons under maximum price
     * @throws CouponNotFoundException   Thrown if the List is empty (Customer has no Coupons under the max price)
     * @throws UnauthorizedUserException Thrown if a client type other than Customer attempts to use this method
     */
    @GetMapping("/coupons/max/{price}") // http://localhost:8080/customer/coupons/max/{price}
    public ResponseEntity<?> getCustomerCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double price) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customerService.readCustomerCouponsByMaxPrice(jwtUtil.getIdFromToken(token), price));
    }


    /**
     * A method that returns the details of the logged-in Customer.
     *
     * @param token Valid, logged Customer's JSON Web Token (JWT)
     * @return New token, 200-Ok HTTP status and Logged-Customer details
     * @throws UnauthorizedUserException Thrown if a client type other than Customer attempts to use this method
     */
    @GetMapping("/details") // http://localhost:8080/customer/details
    public ResponseEntity<?> getCustomerDetail(@RequestHeader(name = "Authorization") String token) throws UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.CUSTOMER)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customerService.readCustomerDetails(jwtUtil.getIdFromToken(token)));
    }
}

