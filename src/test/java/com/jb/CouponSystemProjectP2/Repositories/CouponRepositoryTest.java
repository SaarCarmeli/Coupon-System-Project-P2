package com.jb.CouponSystemProjectP2.Repositories;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import com.jb.CouponSystemProjectP2.Threads.DailyJob;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class CouponRepositoryTest {

    @Autowired
    private CouponRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


    @Test
    void checkIfCouponHasBeenDeletedByEndDateAfter() {
        //given
        Coupon pcCoupon1 = Coupon.builder()
                .amount(2)
                .price(999.99)
                .category(Category.VACATION)
                .title("Caribbean Cruise Coupon")
                .description("5% off on cruise trips in the Caribbean")
                .image("cruise_ship.jpeg")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plus(50, ChronoUnit.DAYS)))
                .build();
        underTest.save(pcCoupon1);
        //when
        pcCoupon1.setEndDate(Date.valueOf(LocalDate.now().minusDays(1)));
        //then
        assertThat(pcCoupon1).is(null);
    }

    @Test
    void existsByTitleAndCompanyId() {
        //given
        //when
        //then
    }

    @Test
    void findByCompanyId() {
        //given
        //when
        //then
    }

    @Test
    void findByCompanyIdAndCategory() {
        //given
        //when
        //then
    }

    @Test
    void findByCompanyIdAndPriceLessThan() {
        //given
        //when
        //then
    }

    @Test
    void findByCustomerId() {
        //given
        //when
        //then
    }

    @Test
    void findByCustomerIdAndCategory() {
        //given
        //when
        //then
    }

    @Test
    void findByCustomerIdAndPriceLessThan() {
        //given
        //when
        //then
    }
}