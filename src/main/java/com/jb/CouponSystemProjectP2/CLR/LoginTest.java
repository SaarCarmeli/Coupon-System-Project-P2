package com.jb.CouponSystemProjectP2.CLR;


import com.jb.CouponSystemProjectP2.Beans.LoginDetails;
import com.jb.CouponSystemProjectP2.Beans.UserType;
import com.jb.CouponSystemProjectP2.Security.JWTutil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@RequiredArgsConstructor
public class LoginTest implements CommandLineRunner {
    private final JWTutil underTest;

    @Override
    public void run(String... args) throws Exception {
        //@GenerateAdminTokenTest
        System.out.println("===============SECURITY TEST===================== \n");
        System.out.println("1. Test if admin Token is generated successfully");
        LoginDetails adminDetails = new LoginDetails("admin@admin.com", "admin", UserType.ADMIN);
        String adminToken = underTest.generateToken(adminDetails);
        System.out.println("Token -> " + adminToken);
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");


//    @extractAllClaimsFromTokenTest
        System.out.println("2. Extract all claims from token test");
        Claims claims = underTest.extractAllClaims(adminToken);
        System.out.println(claims);
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

//    @failedToExtractAllClaimsFromTokenTest

//    @getIdFromTokenTest
        System.out.println("3. Get id from token test");
        System.out.println("Extracted id -> " + underTest.getIdFromToken(adminToken));
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");
//    @failedToGetIdFromTokenTest

//    @getUserTypeFromTokenTest
        System.out.println("4. Get user type from token test");
        System.out.println("Extracted user type -> " + underTest.getUserTypeFromToken(adminToken));
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

//    @failedToGetUserTypeFromToken
    }
}