package com.jb.CouponSystemProjectP2.clr;


import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Repository.CompanyRepository;
import com.jb.CouponSystemProjectP2.Repository.CouponRepository;
import com.jb.CouponSystemProjectP2.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
    }
}
