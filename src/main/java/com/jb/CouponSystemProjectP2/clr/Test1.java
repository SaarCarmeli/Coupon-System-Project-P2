package com.jb.CouponSystemProjectP2.clr;


import com.jb.CouponSystemProjectP2.Repository.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repository.CouponRepository;
import com.jb.CouponSystemProjectP2.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class Test1 implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}