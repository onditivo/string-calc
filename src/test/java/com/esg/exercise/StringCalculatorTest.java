package com.esg.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void given_empty_string_add_returns_sum_of_integers() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void given_string_number_has_only_one_integer_add_returns_sum_of_integers() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    public void given_string_number_has_two_or_more_integers_add_returns_sum_of_integers() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    public void given_string_number_has_new_lines_between_integers_add_returns_sum_of_integers() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    public void given_string_number_has_a_leading_delimiter_and_integers_add_returns_sum_of_integers() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    @Test
    public void given_string_number_has_a_negative_integer_add_throws_exception() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("2,-4,3,-5"));

        String expectedMessage = "Negatives not allowed: -4,-5";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void given_string_number_ignore_numbers_greater_than_1000_add_returns_sum_of_integers() {
        assertEquals(2, StringCalculator.add("1001,2"));
    }

    @Test
    public void given_string_number_has_delimiter_with_any_length_add_returns_sum_of_integers() {
        assertEquals(6, StringCalculator.add("//[|||]\n1|||2|||3"
        ));
    }

    @Test
    public void given_string_number_has_multiple_delimiters_add_returns_sum_of_integers() {
        assertEquals(6, StringCalculator.add("//[|][%]\n1|2%3"
        ));
    }

    @Test
    public void given_string_number_has_multiple_delimiters_with_any_length_add_returns_sum_of_integers() {
        assertEquals(6, StringCalculator.add("//[|||][%%]\n1|||2%%3"));
    }
}
