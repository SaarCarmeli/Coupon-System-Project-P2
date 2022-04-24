package com.jb.CouponSystemProjectP2.clr;


import com.jb.CouponSystemProjectP2.Repositories.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repositories.CouponRepository;
import com.jb.CouponSystemProjectP2.Repositories.CustomerRepository;
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