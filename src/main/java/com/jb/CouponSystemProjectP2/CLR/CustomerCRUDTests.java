package com.jb.CouponSystemProjectP2.CLR;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerNotFoundException;
import com.jb.CouponSystemProjectP2.Services.CustomerService;
import com.jb.CouponSystemProjectP2.Util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
@Order(4)
@RequiredArgsConstructor
public class CustomerCRUDTests implements CommandLineRunner {
    private final CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        //@purchaseCouponTest
        System.out.println("===============CUSTOMER SERVICE TEST===================== \n");
        System.out.println("1. Test if a coupon is purchase successfully");
        Coupon testCoupon = Coupon.builder()
                .id(2)
                .amount(5)
                .price(49.99)
                .category(Category.RESTAURANT)
                .title("Steakhouse in The Ocean Coupon")
                .description("15% off in the cruise ship's steakhouse")
                .image("steak.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(5, ChronoUnit.DAYS)))
                .build();
        System.out.println("Coupon " + testCoupon.getTitle().replace("Coupon", "") + "amount before purchase = " + testCoupon.getAmount());
        customerService.purchaseCoupon(1, testCoupon);
        System.out.println("Coupon " + testCoupon.getTitle().replace("Coupon", "") + "new amount = " + testCoupon.getAmount());
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

//        //@failedToPurchaseCouponTest
        System.out.println("2. Test if a coupon is failed to purchase");
        Coupon testCoupon1 = Coupon.builder()
                .id(9)
                .amount(10)
                .price(100)
                .category(Category.FOOD)
                .title("New hamburger restaurant")
                .description("10% off on 2 burgers with drinks")
                .image("hamburger.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(90, ChronoUnit.DAYS)))
                .build();
        try {
            customerService.purchaseCoupon(1, testCoupon1);
        } catch (CouponNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }

        //@readAllCustomerCouponsTest
        System.out.println("3. Read customer coupons by customer id (id = 1) test");
        try {
            TablePrinter.print(customerService.readAllCustomerCoupons(1));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

        //@failedToReadAllCustomerCouponsTest
        System.out.println("4.Failed to read customer coupons by customer id test");
        try {
            TablePrinter.print(customerService.readAllCustomerCoupons(7));
        } catch (CustomerNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }

        //@readCustomerCouponsByCategoryTest
        System.out.println("5. Read customer coupons by category (FOOD) test");
        TablePrinter.print(customerService.readCustomerCouponsByCategory(1, Category.FOOD));
        System.out.println("Test successfully passed ");
        System.out.println("==================================================== \n");


        //@failedToReadCustomerCouponsByCategory
        System.out.println("6.Failed to read customer coupons by category test");
        try {
            TablePrinter.print(customerService.readCustomerCouponsByCategory(2, Category.FOOD));
        } catch (CouponNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }

        //@readCustomerCouponsByMaxPriceTest
        System.out.println("7.Read customer coupons by max price (500) test");

        TablePrinter.print(customerService.readCustomerCouponsByMaxPrice(1, 500));
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

        //@failedToReadCustomerCouponsByMaxPrice
        System.out.println("8.Failed to read customer coupons by max price (5) test");
        try {
            customerService.readCustomerCouponsByMaxPrice(1, 5);

        } catch (CouponNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }

        //@readCustomerDetails
        System.out.println("9.Read customer details test");
        TablePrinter.print(customerService.readCustomerDetails(1));
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

        //@failedToReadCustomerDetails
        System.out.println("10.Failed to read customer details test");
        try {
            TablePrinter.print(customerService.readCustomerDetails(4));
        } catch (CustomerNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }
    }
}