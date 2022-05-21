package com.jb.CouponSystemProjectP2.Repositories;

import com.jb.CouponSystemProjectP2.Beans.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository underTest;



    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void CheckIfCustomerEmailExists() {
        //given
        String email = "jeff@gmail.com";
        Customer customer = Customer.builder()
                .firstName("Jeffery")
                .lastName("Jefferson")
                .email("jeff@gmail.com")
                .password("123456789")
                .build();
        underTest.save(customer);

        //when
        boolean expectedEmail = underTest.existsByEmail(email);

        //then
        assertThat(expectedEmail).isTrue();
    }

    @Test
    void CheckIfCustomerExistsByEmailAndPassword() {
        //given
        String email = "jeff@gmail.com";
        String password = "123456789";
        Customer customer = Customer.builder()
                .firstName("Jeffery")
                .lastName("Jefferson")
                .email("jeff@gmail.com")
                .password("123456789")
                .build();
        underTest.save(customer);

        //when

        boolean expectedEmailAndPassword = underTest.existsByEmailAndPassword(email, password);
        //then
        assertThat(expectedEmailAndPassword).isTrue();
    }

    @Test
    void findCustomerIdByEmailAndPassword() {
        //given
        int expectedId;
        String password = "123456789";
        String email = "jeff@gmail.com";
        Customer customer = Customer.builder()
                .firstName("Jeffery")
                .lastName("Jefferson")
                .email("jeff@gmail.com")
                .password("123456789")
                .build();
        underTest.save(customer);

        //when
         expectedId = underTest.findIdByEmailAndPassword(email, password);

        //then
        assertThat(expectedId).isEqualTo(customer.getId());
    }


    @Test
    void CheckIfCustomerEmailDoesNotExists() {
        //given
        String email = "jeff@gmail.com";

        //when
        boolean expectedEmail = underTest.existsByEmail(email);

        //then
        assertThat(expectedEmail).isFalse();
    }
}
