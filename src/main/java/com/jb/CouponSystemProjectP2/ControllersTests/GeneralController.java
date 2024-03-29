package com.jb.CouponSystemProjectP2.ControllersTests;

import com.jb.CouponSystemProjectP2.Services.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/") // http://localhost:8080
@RequiredArgsConstructor
public class GeneralController {
    private final GeneralService generalService;

    /**
     * A method that returns all coupons in the database
     *
     * @return 200-Ok HTTP status and a List of all Coupons
     */
    @GetMapping("/coupons/all") // http://localhost:8080/coupons/all
    public ResponseEntity<?> getAllCoupons() {
        return ResponseEntity.ok().body(generalService.readAllCoupons());
    }
}
