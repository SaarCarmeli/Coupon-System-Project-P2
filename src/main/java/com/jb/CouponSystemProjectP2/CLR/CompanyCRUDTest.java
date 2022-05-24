package com.jb.CouponSystemProjectP2.CLR;


import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Services.CompanyService;
import com.jb.CouponSystemProjectP2.Util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
@Order(3)
@RequiredArgsConstructor
public class CompanyCRUDTest implements CommandLineRunner {
    private final CompanyService companyService;

    @Override
    public void run(String... args) throws Exception {

        //@createCouponTest
        System.out.println("===============COMPANY SERVICE TEST===================== \n");
        System.out.println("1. Test if a coupon is created");
        Coupon testCoupon = Coupon.builder()
                .amount(4)
                .price(799.99)
                .category(Category.VACATION)
                .title("Maldives Cruise Coupon")
                .description("5% off on cruise trips in the Maldives")
                .image("cruise_ship.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(50, ChronoUnit.DAYS)))
                .build();
        companyService.createCoupon(1, testCoupon);
        TablePrinter.print(companyService.readAllCompanyCoupons(1));
        System.out.println("Company has been successfully created, test success");
        System.out.println("==================================================== \n");


        //@failedToCreateCouponTest
        System.out.println("==================================================== \n");
        System.out.println("2. Test if a coupon is not created");
        Coupon testCoupon1 = Coupon.builder()
                .amount(4)
                .price(799.99)
                .category(Category.VACATION)
                .title("Maldives Cruise Coupon")
                .description("5% off on cruise trips in the Maldives")
                .image("cruise_ship.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(50, ChronoUnit.DAYS)))
                .build();
        companyService.createCoupon(1, testCoupon1);
        TablePrinter.print(companyService.readAllCompanyCoupons(1));
        System.out.println("Coupon has not created successfully created, test success");
        System.out.println("==================================================== \n");
    }
}