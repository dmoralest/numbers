package com.kklte.numbers.services;

import com.kklte.numbers.ApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class NumberTranslateServiceImplTest extends ApplicationTest {

    @Autowired
    private INumberTranslateService service;

    @Test
    public void testZeroIsTranslated() {
        final String expected = "Zero";
        final String actual = service.translate(0L);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleDigitNumberIsTranslated() {
        final String[] expected = new String[]{"One", "Two", "Seven", "Three"};
        final String[] actual = Stream.of(1L, 2L, 7L, 3L)
                .map(service::translate)
                .toArray(String[]::new);
        assertThat(actual).containsExactly(expected);
    }

    @Test
    public void testTwoDigitNumberIsTranslated() {
        final String[] expected = new String[]{"Fifteen", "Fourteen", "Eighteen", "Sixty one", "Forty three",
                "Ninety eight"};
        final String[] actual = Stream.of(15L, 14L, 18L, 61L, 43L, 98L)
                .map(service::translate)
                .toArray(String[]::new);
        assertThat(actual).containsExactly(expected);
    }

    @Test
    public void  testThreeDigitNumberIsTranslated() {
        final String[] expected = new String[]{
                "One hundred and twenty three",
                "One hundred and fifty seven",
                "Four hundred and twenty nine",
                "Seven hundred and sixty",
                "One hundred",
                "Nine hundred and ninety nine"};
        final String[] actual = Stream.of(123L, 157L, 429L, 760L, 100L, 999L)
                .map(service::translate)
                .toArray(String[]::new);
        assertThat(actual).containsExactly(expected);
    }

    @Test
    public void testFourDigitNumberIsTranslated() {
        final String[] expected = new String[]{
                "One thousand five hundred and fifty two",
                "Four thousand six hundred and ninety nine",
                "Nine thousand and ninety nine",
                "Five thousand two hundred and six",
                "Seven thousand"};
        final String[] actual = Stream.of(1552L, 4699L, 9099L, 5206L, 7000L)
                .map(service::translate)
                .toArray(String[]::new);
        assertThat(actual).containsExactly(expected);
    }

    @Test
    public void testLargeDigitNumberIsTranslated() {
        final String[] expected = new String[]{
                "Fifty two thousand four hundred and twenty three",
                "Seven hundred thousand and six",
                "Four hundred thousand eight hundred",
                "Two million six hundred thousand one hundred and seventy two",
                "Thirty million two hundred and sixty one",
                "Eighty million three hundred and forty three",
                "Three hundred million four hundred and twenty three thousand and fifty two",
                "Two hundred and forty six billion"
        };
        final String[] actual = Stream.of(52423L, 700006L, 400800L, 2600172L, 30000261L, 80000343L, 300423052L,
                246000000000L)
                .map(service::translate)
                .toArray(String[]::new);
        assertThat(actual).containsExactly(expected);
    }

    @Test
    public void testNegativeNumberIsTranslated() {
        final String[] expected = new String[]{
                "Minus one hundred and twenty three",
                "Minus one thousand five hundred and fifty two",
                "Minus fifty two thousand four hundred and twenty three",
                "Zero"
        };
        final String[] actual = Stream.of(-123L, -1552L, -52423L, -0L)
                .map(service::translate)
                .toArray(String[]::new);
        assertThat(actual).containsExactly(expected);
    }

}
