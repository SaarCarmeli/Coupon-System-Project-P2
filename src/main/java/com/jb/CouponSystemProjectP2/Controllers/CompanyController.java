package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.UserType;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.UnauthorizedUserException;
import com.jb.CouponSystemProjectP2.Security.JWTutil;
import com.jb.CouponSystemProjectP2.Services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * RESTful web service class (RestController) for Company client type.
 */
@RestController
@RequestMapping("/company") // http://localhost:8080/company
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final JWTutil jwtUtil;

    /**
     * A method to create a new Coupon entity for a Company client.
     *
     * @param token  Valid, logged Company's JSON Web Token (JWT)
     * @param coupon New Coupon entity to be added under the Company
     * @return New token and 202-Accepted HTTP status
     * @throws CompanyException          Thrown if the Coupon already exists in the Company under the new Coupon's title
     * @throws UnauthorizedUserException Thrown if a client type other than Company attempts to use this method
     */
    @PostMapping("/coupons/add") // http://localhost:8080/company/coupons/add
    public ResponseEntity<?> createNewCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CompanyException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)) {
            throw new UnauthorizedUserException();
        }
        companyService.createCoupon(jwtUtil.getIdFromToken(token), coupon);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    /**
     * A method that returns all the Company's Coupons to the client.
     *
     * @param token Valid, logged Company's JSON Web Token (JWT)
     * @return New token, 200-Ok HTTP status and List of all Company's Coupons
     * @throws CouponNotFoundException   Thrown if the List is empty (Company has no Coupons)
     * @throws UnauthorizedUserException Thrown if a client type other than Company attempts to use this method
     */
    @GetMapping("/coupons/all") // http://localhost:8080/company/coupons/all
    public ResponseEntity<?> getAllCompanyCoupons(@RequestHeader(name = "Authorization") String token) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readAllCompanyCoupons(jwtUtil.getIdFromToken(token)));
    }

    /**
     * A method that returns Company's Coupons of a certain Category to the client.
     *
     * @param token    Valid, logged Company's JSON Web Token (JWT)
     * @param category Coupon Category to select by
     * @return New token, 200-Ok HTTP status and List of Company's Coupons by given Category
     * @throws CouponNotFoundException   Thrown if the List is empty (Company has no Coupons in that Category)
     * @throws UnauthorizedUserException Thrown if a client type other than Company attempts to use this method
     */
    @GetMapping("/coupons/cat/{category}") // http://localhost:8080/company/coupons/cat/{category}
    public ResponseEntity<?> getCompanyCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readCompanyCouponsByCategory(jwtUtil.getIdFromToken(token), category));
    }

    /**
     * A method that returns Company's Coupons under a certain price to the client.
     *
     * @param token Valid, logged Company's JSON Web Token (JWT)
     * @param price Maximum price
     * @return New token, 200-Ok HTTP status and List of Company's Coupons under maximum price
     * @throws CouponNotFoundException   Thrown if the List is empty (Company has no Coupons under the max price)
     * @throws UnauthorizedUserException Thrown if a client type other than Company attempts to use this method
     */
    @GetMapping("/coupons/max/{price}") // http://localhost:8080/company/coupons/max/{price}
    public ResponseEntity<?> getCompanyCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double price) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readCompanyCouponsByMaxPrice(jwtUtil.getIdFromToken(token), price));
    }

    /**
     * A method that returns the details of the logged-in Company.
     *
     * @param token Valid, logged Company's JSON Web Token (JWT)
     * @return New token, 200-Ok HTTP status and Logged-Company details
     * @throws UnauthorizedUserException Thrown if a client type other than Company attempts to use this method
     */
    @GetMapping("/details") // http://localhost:8080/company/details
    public ResponseEntity<?> getCompanyDetail(@RequestHeader(name = "Authorization") String token) throws UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)) {
            throw new UnauthorizedUserException();
        }
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(companyService.readCompanyDetails(jwtUtil.getIdFromToken(token)));
    }

    /**
     * A method to update a Company's Coupon.
     *
     * @param token  Valid, logged Company's JSON Web Token (JWT)
     * @param coupon Coupon entity for update
     * @return New token and 202-Accepted HTTP status
     * @throws CouponNotFoundException   Thrown if the Coupon ID number is not found under the logged-Company
     * @throws UnauthorizedUserException Thrown if a client type other than Company attempts to use this method
     */
    @PutMapping("/coupons/update") // http://localhost:8080/company/coupons/update
    public ResponseEntity<?> updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)) {
            throw new UnauthorizedUserException();
        }
        companyService.updateCoupon(jwtUtil.getIdFromToken(token), coupon);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    /**
     * A method to delete a Company's Coupon.
     *
     * @param token    Valid, logged Company's JSON Web Token (JWT)
     * @param couponId ID number of the Coupon to be deleted
     * @return New token and 202-Accepted HTTP status
     * @throws CouponNotFoundException   Thrown if the Coupon ID number is not found under the logged-Company
     * @throws UnauthorizedUserException Thrown if a client type other than Company attempts to use this method
     */
    @DeleteMapping("/coupons/delete/{couponId}") // http://localhost:8080/company/coupons/delete/{couponId}
    public ResponseEntity<?> deleteCouponById(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws CouponNotFoundException, UnauthorizedUserException {
        if (!jwtUtil.getUserTypeFromToken(token).equals(UserType.COMPANY)) {
            throw new UnauthorizedUserException();
        }
        companyService.deleteCouponById(jwtUtil.getIdFromToken(token), couponId);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }
}
