package com.vinaylogics.students;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class StudentApplicationTests {

    Calculator underTest = new Calculator();

    @Test
    void itShouldAddTwoNumbers() {
        // given
        int number1 = 20;
        int number2 = 30;

        // when
        int result = underTest.add(number1, number2);

        // then
        int expected = 50;
        assertThat(result).isEqualTo(expected);
    }


    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }
}
