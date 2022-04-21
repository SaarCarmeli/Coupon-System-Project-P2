package com.jb.CouponSystemProjectP2.Repository;

import com.jb.CouponSystemProjectP2.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByNameOrEmail(String name, String email);
}
