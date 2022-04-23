package com.jb.CouponSystemProjectP2.clr;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class TestInit implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Companies (3):
        Company princessCruises = Company.builder()
                .name("Princess Cruises")
                .email("princess@cruises.com")
                .password("crs123")
                .build();
        Company apple = Company.builder()
                .name("Apple")
                .email("apple@apple.com")
                .password("sameAsEmail")
                .build();
        Company amazon = Company.builder()
                .email("amazon@amazon.com")
                .password("jungle")
                .build();

        // Customers (2):
        Customer jeffery = Customer.builder()
                .firstName("Jeffery")
                .lastName("Jefferson")
                .email("jeff@gmail.com")
                .password("123456789")
                .build();
        Customer jennifer = Customer.builder()
                .firstName("Jennifer")
                .lastName("miller")
                .email("jen@gmail.com")
                .password("987654321")
                .build();

        // Coupons (8):
        Coupon pcCoupon1 = Coupon.builder()
                .amount(2)
                .price(999.99)
                .category(Category.VACATION)
                .title("Caribbean Cruise Coupon")
                .description("5% off on cruise trips in the Caribbean")
                .image("cruise_ship.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(50, ChronoUnit.DAYS)))
                .build();
        Coupon pcCoupon2 = Coupon.builder()
                .amount(5)
                .price(49.99)
                .category(Category.RESTAURANT)
                .title("Steakhouse in The Ocean Coupon")
                .description("15% off in the cruise ship's steakhouse")
                .image("steak.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(5, ChronoUnit.DAYS)))
                .build();
        princessCruises.setCoupons(List.of(pcCoupon1, pcCoupon2));

        Coupon apCoupon1 = Coupon.builder()
                .amount(6)
                .price(9.99)
                .category(Category.FOOD)
                .title("Fresh Apple Coupon")
                .description("20% off on fresh apples from the farm")
                .image("red_apple.jpg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(3, ChronoUnit.MONTHS)))
                .build();
        apple.setCoupons(List.of(apCoupon1));

        Coupon amCoupon1 = Coupon.builder()
                .amount(3)
                .price(175)
                .category(Category.HOME_APPLIANCES)
                .title("Refrigerator Coupon")
                .description("10% off on new refrigerators")
                .image("refrigerator.jpg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(60, ChronoUnit.DAYS)))
                .build();
        Coupon amCoupon2 = Coupon.builder()
                .amount(2)
                .price(99.99)
                .category(Category.ELECTRONICS)
                .title("PC Coupon")
                .description("15% off on new PCs")
                .image("computer.jpg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(30, ChronoUnit.DAYS)))
                .build();
        Coupon amCoupon3 = Coupon.builder()
                .amount(2)
                .price(24.99)
                .category(Category.HOME_APPLIANCES)
                .title("Microwave Coupon")
                .description("12% off on new microwaves")
                .image("microwave.jpg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(15, ChronoUnit.DAYS)))
                .build();
        Coupon amCoupon4 = Coupon.builder()
                .amount(4)
                .price(100.50)
                .category(Category.ELECTRONICS)
                .title("Computer Monitor Coupon")
                .description("7% off on new computer monitors")
                .image("monitor.jpg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(30, ChronoUnit.DAYS)))
                .build();
        Coupon amCoupon5 = Coupon.builder()
                .amount(7)
                .price(100)
                .category(Category.SOFTWARE)
                .title("Photoshop License Coupon")
                .description("12% off on photoshop subscription for 2 years")
                .image("photoshop_logo.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(75, ChronoUnit.DAYS)))
                .build();
        amazon.setCoupons(List.of(amCoupon1, amCoupon2, amCoupon3, amCoupon4, amCoupon5));
    }
}
