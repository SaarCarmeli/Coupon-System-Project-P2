package com.jb.CouponSystemProjectP2.Repositories;

import com.jb.CouponSystemProjectP2.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);
    boolean existsByIdAndEmailAndPassword(int id, String email, String password);
}
