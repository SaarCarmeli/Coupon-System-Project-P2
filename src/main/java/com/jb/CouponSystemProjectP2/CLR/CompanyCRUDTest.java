package com.jb.CouponSystemProjectP2.CLR;


import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Services.CompanyService;
import com.jb.CouponSystemProjectP2.Threads.DailyJob;
import com.jb.CouponSystemProjectP2.Util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@Order(3)
@RequiredArgsConstructor
public class CompanyCRUDTest implements CommandLineRunner {
    private final CompanyService companyService;


    @Override
    public void run(String... args) throws Exception {

//        //@createCouponTest
        System.out.println("===============COMPANY SERVICE TEST===================== \n");
        System.out.println("1. Test if a coupon is created");
        Coupon testCoupon = Coupon.builder()
                .amount(4)
                .price(799.99)
                .category(Category.VACATION)
                .title("Weekend in the Maldives Coupon")
                .description("9% off on 5 star hotel for weekend in the Maldives")
                .image("maldives.jpeg")
                .startDate(Date.valueOf(LocalDate.now().minus(90, ChronoUnit.DAYS)))
                .endDate(Date.valueOf(LocalDate.now().minus(10, ChronoUnit.DAYS)))
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
                .title("Weekend in the Maldives Coupon")
                .description("5% off on 5 star hotel for weekend in the Maldives")
                .image("maldives.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minus(10, ChronoUnit.DAYS)))
                .build();

        try {
            companyService.createCoupon(1, testCoupon1);
        } catch (CompanyException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Coupon has not created successfully created, test success");
            System.out.println("==================================================== \n");
        }

        //@readAllCompanyCouponsTest
        System.out.println("3. Read all company coupons test");
        List<Coupon> testCoupons = companyService.readAllCompanyCoupons(1);
        TablePrinter.print(testCoupons);
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

        //@failedToReadAllCompanyCouponsTest
        System.out.println("4. Failed to read all company coupons test");
        try {
            List<Coupon> testCoupons1 = companyService.readAllCompanyCoupons(4);
            TablePrinter.print(testCoupons1);
        } catch (CouponNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }

        //@readCompanyCouponsByCategoryTest
        System.out.println("5. Read company coupons by category test. category will be VACATION");
        TablePrinter.print(companyService.readCompanyCouponsByCategory(1, Category.VACATION));
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

        //@failedToReadCompanyCouponsByCategoryTest
        System.out.println("6. Failed to read coupons by category test");
        try {
            TablePrinter.print(companyService.readCompanyCouponsByCategory(1, Category.ELECTRONICS));
        } catch (CouponNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
        }
        //@readCompanyCouponsByMaxPrice
        System.out.println("==================================================== \n");
        System.out.println("7. Read company coupons by max price of -> 1000");
        TablePrinter.print(companyService.readCompanyCouponsByMaxPrice(1, 1000));
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");
        //@failedToReadCompanyCouponsByMaxPrice
        System.out.println("8. Failed to read company coupons by max price");
        try {
            TablePrinter.print(companyService.readCompanyCouponsByMaxPrice(1, 30));
        } catch (CouponNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }
        //@updateCouponTest
        System.out.println("9. Update coupon test");
        Coupon updateTestCoupon1 = Coupon.builder()
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
        companyService.updateCoupon(1, updateTestCoupon1);
        TablePrinter.print(updateTestCoupon1);
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

        //@failedToUpdateCouponTest
        System.out.println("10. Failed to update coupon test");
        Coupon updateTestCoupon2 = Coupon.builder()
                .id(14)
                .amount(1)
                .price(599.99)
                .category(Category.VACATION)
                .title("coupon under test")
                .description("10% off on cruise trips in the Maldives")
                .image("cruise_ship.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(50, ChronoUnit.DAYS)))
                .build();
        try {
            companyService.updateCoupon(1, updateTestCoupon2);
        } catch (CouponNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }
        //@deleteCouponByIdTest
        System.out.println("11. Delete coupon by id = 9 test");
        companyService.deleteCouponById(1,9);
        TablePrinter.print(companyService.readAllCompanyCoupons(1));
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

        //@FailedToDeleteCouponTest
        System.out.println("12. Failed to delete coupon by id test");
        try {
            companyService.deleteCouponById(1,9);
        } catch (CouponNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }

        //@readCompanyDetailsTest
        System.out.println("13. Read company details test");
        TablePrinter.print(companyService.readCompanyDetails(1));
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

        //@failedToReadCompanyDetailsTest
        System.out.println("14. Failed to read company details test");
        try {
            companyService.readCompanyDetails(4);
        } catch (CompanyNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Test successfully failed");
            System.out.println("==================================================== \n");
        }
    }
}