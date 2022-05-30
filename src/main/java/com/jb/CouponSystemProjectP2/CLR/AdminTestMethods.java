package com.jb.CouponSystemProjectP2.CLR;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.SQLException;

@Transactional
@Service
@RequiredArgsConstructor
public class AdminTestMethods {
    @Autowired
    private final EntityManager entityManager;

    public void clearAllCompaniesInTheDataBase() throws SQLException {
        String query = "TRUNCATE TABLE coupon_project_p2.companies;";
        entityManager.createNativeQuery(query).executeUpdate();
    }

    public void clearAllCustomersInTheDataBase() throws SQLException {
        String query = "TRUNCATE TABLE coupon_project_p2.customers;";
        entityManager.createNativeQuery(query).executeUpdate();
    }

    public void clearAllCouponsInTheDataBase() throws SQLException {
        String query = "TRUNCATE TABLE coupon_project_p2.coupons;";
        entityManager.createNativeQuery(query).executeUpdate();
    }
}
