package org.calcstasktests;

import org.calcstask.CalculatorWithMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculatorWithMemoryTests {

    private CalculatorWithMemory calculator;

    @BeforeEach
    public void initCalculatorForEach() {
        this.calculator = new CalculatorWithMemory();
    }

    public static Stream<Arguments> invalidNumbersAsStringDataProvider() {
        return Stream.of(
                Arguments.of("6e1", "2"),
                Arguments.of("6", "2t3"),
                Arguments.of("6e1", "2t3"),
                Arguments.of("6e1", null),
                Arguments.of(null, "2t3"),
                Arguments.of(null, null)
        );
    }


    @ParameterizedTest
    @CsvSource({"6.0, 3.0, 2.0", "3.5, 0.5, 7.0", "0.9, 3.1, 0.2903225806451613", "-3.4, 3.0, -1.1333333333333333"})
    public void testDivisionNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        double result = calculator.division(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 2", "3, 1, 3", "1, 3, 0", "-3, 3, -1"})
    public void testDivisionNormalInputReturnsValidResult(int firstNumber, int secondNumber, double expected) {
        double result = calculator.division(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 2", "3, 1, 3", "1, 3, 0", "-3, 3, -1"})
    public void testDivisionNormalInputReturnsValidResult(long firstNumber, long secondNumber, double expected) {
        double result = calculator.division(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6.0, 3.0, 2.0", "3.5, 0.5, 7.0", "0.9, 3.1, 0.2903225806451613", "-3.4, 3.0, -1.1333333333333333"})
    public void testDivisionNormalInputReturnsValidResult(String firstNumber, String secondNumber, double expected) {
        double result = calculator.division(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testDivisionSecondNumberZeroInputThrowsExpectedException() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.division(1.0, 0.0);
        });
    }

    @Test
    public void testDivisionSecondNumberZeroInputIntegerThrowsExpectedException() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.division(1, 0);
        });
    }

    @Test
    public void testDivisionSecondNumberZeroInputLongThrowsExpectedException() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.division(1L, 0);
        });
    }

    @Test
    public void testDivisionSecondNumberZeroInputStringThrowsExpectedException() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.division("1", "0");
        });
    }

    @ParameterizedTest
    @MethodSource("invalidNumbersAsStringDataProvider")
    public void testDivisionInvalidInputThrowsExpectedException(String firstNumber, String secondNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.division(firstNumber, secondNumber);
        });
    }

    @ParameterizedTest
    @CsvSource({"6.1, 3, 18.299999999999997", "3.0, 0.5, 1.5", "0.9, 3, 2.7", "-3.1, 3.2, -9.920000000000002"})
    public void testMultiplicationNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        double result = calculator.multiplication(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 18", "3, 5, 15", "-9, 3, -27", "-3, 3, -9"})
    public void testMultiplicationNormalInputReturnsValidResult(int firstNumber, int secondNumber, double expected) {
        double result = calculator.multiplication(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 18", "3, 5, 15", "-9, 3, -27", "-3, 3, -9"})
    public void testMultiplicationNormalInputReturnsValidResult(long firstNumber, long secondNumber, double expected) {
        double result = calculator.multiplication(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 18", "3, 0.5, 1.5", "0.9, 3, 2.7", "-3, 3, -9"})
    public void testMultiplicationNormalInputReturnsValidResult(String firstNumber, String secondNumber, double expected) {
        double result = calculator.multiplication(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("invalidNumbersAsStringDataProvider")
    public void testMultiplicationInvalidInputThrowsExpectedException(String firstNumber, String secondNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.multiplication(firstNumber, secondNumber);
        });
    }

    @ParameterizedTest
    @CsvSource({"6.0, 3.1, 2.9", "3.5, 0.5, 3.0", "0.9, 3.1, -2.2", "-3.4, 3.0, -6.4"})
    public void testsMinusNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        double result = calculator.minus(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 3", "3, 1, 2", "1, 3, -2", "-3, 3, -6"})
    public void testsMinusNormalInputReturnsValidResult(int firstNumber, int secondNumber, double expected) {
        double result = calculator.minus(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 3", "3, 1, 2", "1, 3, -2", "-3, 3, -6"})
    public void testsMinusNormalInputReturnsValidResult(long firstNumber, long secondNumber, double expected) {
        double result = calculator.minus(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 3", "3, 3, 0", "1, 3, -2", "-3, 3, -6"})
    public void testsMinusNormalInputReturnsValidResult(String firstNumber, String secondNumber, double expected) {
        double result = calculator.minus(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("invalidNumbersAsStringDataProvider")
    public void testMinusInvalidInputThrowsExpectedException(String firstNumber, String secondNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.minus(firstNumber, secondNumber);
        });
    }

    @ParameterizedTest
    @CsvSource({"6.1, 3.0, 9.1", "3.5, 0.5, 4.0", "0.8, 3.2, 4.0", "-3.0, 3.0, 0.0"})
    public void testsAddingNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        double result = calculator.adding(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 9", "3, 5, 8", "2, 3, 5", "-11, 3, -8"})
    public void testsAddingNormalInputReturnsValidResult(int firstNumber, int secondNumber, double expected) {
        double result = calculator.adding(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 9", "3, 5, 8", "2, 3, 5", "-11, 3, -8"})
    public void testsAddingNormalInputReturnsValidResult(long firstNumber, long secondNumber, double expected) {
        double result = calculator.adding(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"-6, -3, -9", "3, 3, 6", "1, 3, 4", "-3, 3, 0"})
    public void testsAddingNormalInputReturnsValidResult(String firstNumber, String secondNumber, double expected) {
        double result = calculator.adding(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("invalidNumbersAsStringDataProvider")
    public void testAddingInvalidInputThrowsExpectedException(String firstNumber, String secondNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.adding(firstNumber, secondNumber);
        });
    }

    @Test
    public void testSave() {
        calculator.adding(2, 2);
        calculator.save();
        Assertions.assertEquals(4, calculator.load());
    }

    @Test
    public void testLoadWithoutSave() {
        calculator.adding(2, 2);
        Assertions.assertEquals(0, calculator.load());
    }

}
