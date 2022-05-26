package com.jb.CouponSystemProjectP2.CLR;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
import com.jb.CouponSystemProjectP2.Services.AdministratorService;
import com.jb.CouponSystemProjectP2.Services.CompanyService;
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
    private final CustomerRepository customerRepository;

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
        System.out.println("Coupon "+testCoupon.getTitle().replace("Coupon","")+" amount before purchase = " +testCoupon.getAmount());
        customerService.purchaseCoupon(1, testCoupon);
        System.out.println("Coupon "+testCoupon.getTitle().replace("Coupon","")+ "new amount = " +testCoupon.getAmount());
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
        try{
            customerService.purchaseCoupon(1,testCoupon1);
        }
            catch (CouponNotFoundException exception){
                System.out.println(exception.getMessage());
                System.out.println("Test successfully failed");
                System.out.println("==================================================== \n");
        }

        //@readAllCustomerCouponsTest
        System.out.println("3. Read customer coupons by customer id test");
        TablePrinter.print(customerService.readAllCustomerCoupons(1));
        System.out.println("Test successfully passed");
        System.out.println("==================================================== \n");

        //@failedToReadAllCustomerCouponsTest
//        Customer testCustomer = Customer.builder()
//                .email("customer@test.com")
//                .firstName("under")
//                .lastName("test")
//                .password("test12345")
//                .build();
//            customerRepository.save(testCustomer);
        //@readCustomerCouponsByCategoryTest
        //@failedToReadCustomerCouponsByCategory
        //@readCustomerCouponsByMaxPriceTest
        //@failedToReadCustomerCouponsByMaxPrice
        //@readCustomerDetails
        //@failedToReadCustomerDetails
    }
}
//  void purchaseCoupon(int customerId, Coupon coupon) throws CouponException, CustomerException, CouponNotFoundException;
//    List<Coupon> readAllCustomerCoupons(int customerId) throws CouponNotFoundException;
//    List<Coupon> readCustomerCouponsByCategory(int customerId, Category category) throws CouponNotFoundException;
//    List<Coupon> readCustomerCouponsByMaxPrice(int customerId, double price) throws CouponNotFoundException;
//    Customer readCustomerDetails(int customerId);