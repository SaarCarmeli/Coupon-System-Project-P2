package com.jb.CouponSystemProjectP2.Repositories;

import com.jb.CouponSystemProjectP2.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmailAndIdNot(String email, int id);

    @Query(value = "SELECT customer_id FROM `customers` WHERE `email` = :email AND `password` = :password", nativeQuery = true)
    int findIdByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
