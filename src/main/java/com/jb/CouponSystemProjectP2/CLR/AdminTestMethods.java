package com.jb.CouponSystemProjectP2.CLR;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class AdminTestMethods {
    @Autowired
    private final EntityManager entityManager;

    public void clearAllCompaniesInTheDataBase() {
        String query = "TRUNCATE TABLE coupon_project_p2.companies;";
        entityManager.createNativeQuery(query).executeUpdate();
    }

    public void clearAllCustomersInTheDataBase() {
        String query = "TRUNCATE TABLE coupon_project_p2.customers;";
        entityManager.createNativeQuery(query).executeUpdate();
    }

    public void clearAllCouponsInTheDataBase() {
        String query = "TRUNCATE TABLE coupon_project_p2.coupons;";
        entityManager.createNativeQuery(query).executeUpdate();
    }
}
