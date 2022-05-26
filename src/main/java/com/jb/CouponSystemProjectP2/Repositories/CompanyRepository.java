package com.jb.CouponSystemProjectP2.Repositories;

import com.jb.CouponSystemProjectP2.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByNameOrEmail(String name, String email);
    boolean existsByEmailAndPassword(String email, String password);
    @Query(value = "SELECT company_id FROM `coupon_project_p2`.`companies` WHERE `email` = :email AND `password` = :password", nativeQuery = true)
    int findIdByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
