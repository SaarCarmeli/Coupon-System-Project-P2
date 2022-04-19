package com.jb.CouponSystemProjectP2.clr;


import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Repository.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repository.CouponRepository;
import com.jb.CouponSystemProjectP2.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Order(1)
@Component
@RequiredArgsConstructor
public class Test1 implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        Company company1 = Company.builder()
                .name("Google")
                .email("Google@gmail.com").password("1234535").build();
        companyRepository.save(company1);

       // Instant now = Instant.now();
        Coupon coupon = Coupon.builder()
                .amount(3).
                category(Category.ELECTRICITY).
                description("Oven").
                image("image").
                price(150).
                title("Big Oven")
                //startDate(Date.valueOf(LocalDate.now())).
               // endDate(Date.valueOf(LocalDate.now().plusMonths(3)))
                .build();
        //couponRepository.save(coupon);

        Customer customer = Customer.builder().
                firstName("aviv").
                lastName("mond").
                email("aviv@Gmail.com").
                coupons(Collections.singletonList(coupon)).
                password("12345678").build();
        customerRepository.save(customer);
    }
}