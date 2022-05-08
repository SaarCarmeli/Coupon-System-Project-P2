package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.LoginDetails;
import com.jb.CouponSystemProjectP2.Exceptions.LoginException;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
import com.jb.CouponSystemProjectP2.Security.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {
    private final JWTutil jwtUtil;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) throws LoginException {
        switch (loginDetails.getUserType()) {
            case Admin:
                if (loginDetails.getEmail().equals("admin@admin.com") && loginDetails.getPassword().equals("admin")) {
                    return new ResponseEntity<>(jwtUtil.generateToken(loginDetails), HttpStatus.ACCEPTED);
                }
                throw new LoginException("User is not an admin! Check login details!");
            case Company:
                if (companyRepository.existsByIdAndEmailAndPassword(loginDetails.getId(), loginDetails.getEmail(), loginDetails.getPassword())) {
                    return new ResponseEntity<>(jwtUtil.generateToken(loginDetails), HttpStatus.ACCEPTED);
                }
                throw new LoginException();
            case Customer:
                if (customerRepository.existsByIdAndEmailAndPassword(loginDetails.getId(), loginDetails.getEmail(), loginDetails.getPassword())) {
                    return new ResponseEntity<>(jwtUtil.generateToken(loginDetails), HttpStatus.ACCEPTED);
                }
                throw new LoginException();
            default:
                throw new LoginException("Invalid user type! Check login details!");
        }
    }
}
