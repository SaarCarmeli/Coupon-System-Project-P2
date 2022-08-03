package com.jb.CouponSystemProjectP2.ControllersTests;

import com.jb.CouponSystemProjectP2.Beans.LoginDetails;
import com.jb.CouponSystemProjectP2.Exceptions.LoginException;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
import com.jb.CouponSystemProjectP2.Security.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A RESTful web service class for the implementation of a login system. Allows registered users to authenticate their authorization to use the Coupon System.
 */
@RestController
@RequestMapping("/user") // http://localhost:8080/user
@RequiredArgsConstructor
public class LoginController {
    private final JWTutil jwtUtil;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    /**
     * A method to grant users access to the methods of the Coupon system by returning a JSON Web Token (JWT) after
     * authentication of user authorization.
     *
     * @param loginDetails Include user email, password and user type. User ID is retrieved from the database for Customer and Company users.
     * @return JSON Web Token (JWT) with user login details
     * @throws LoginException Thrown if user is not found within type or if type does not exist
     */
    @PostMapping("/login") // http://localhost:8080/user/login
    public ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) throws LoginException {
        LoginDetails newLoginDetails = loginDetails;
        switch (newLoginDetails.getUserType()) {
            case ADMIN:
                if (newLoginDetails.getEmail().equals("admin@admin.com") && newLoginDetails.getPassword().equals("admin")) {
                    return ResponseEntity.accepted()
                            .header("Authorization", jwtUtil.generateToken(newLoginDetails))
                            .build();
                }
                throw new LoginException("User is not an admin! Check login details!");
            case COMPANY:
                if (companyRepository.existsByEmailAndPassword(newLoginDetails.getEmail(), newLoginDetails.getPassword())) {
                    newLoginDetails.setId(companyRepository.findIdByEmailAndPassword(newLoginDetails.getEmail(), newLoginDetails.getPassword()));
                    return ResponseEntity.accepted()
                            .header("Authorization", jwtUtil.generateToken(newLoginDetails))
                            .build();
                }
                throw new LoginException("Company user not found!");
            case CUSTOMER:
                if (customerRepository.existsByEmailAndPassword(newLoginDetails.getEmail(), newLoginDetails.getPassword())) {
                    newLoginDetails.setId(customerRepository.findIdByEmailAndPassword(newLoginDetails.getEmail(), newLoginDetails.getPassword()));
                    return ResponseEntity.accepted()
                            .header("Authorization", jwtUtil.generateToken(newLoginDetails))
                            .build();
                }
                throw new LoginException("Customer user not found!");
            default:
                throw new LoginException("Invalid user type! Check login details!");
        }
    }
}
