package com.jb.CouponSystemProjectP2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CouponSystemProjectSaarAndAvivP2ApplicationTests {

    Calculator underTest = new Calculator();
    @Test
    void itShouldAddTwoNumbers() {
        //given
        int num1 = 20;
        int num2 = 30;

        //when
        int result = underTest.add(num1,num2);

        //then
        int expected = 50;
        assertThat(result).isEqualTo(expected);
    }

    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }
}

