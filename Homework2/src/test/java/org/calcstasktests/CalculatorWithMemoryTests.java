package org.calcstasktests;

import org.calcstask.CalculatorWithMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorWithMemoryTests {

    @ParameterizedTest
    @CsvSource({"6, 3, 2", "3, 0.5, 6", "0.9, 3, 0.3", "-3, 3, -1"})
    public void testDivisionNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        CalculatorWithMemory calculator = new CalculatorWithMemory();
        double result = calculator.division(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testDivisionSecondNumberZeroInputThrowsExpectedException() {
        CalculatorWithMemory calculator = new CalculatorWithMemory();
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.division(1, 0);
        });
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 18", "3, 0.5, 1.5", "0.9, 3, 2.7", "-3, 3, -9"})
    public void testMultiplicationNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        CalculatorWithMemory calculator = new CalculatorWithMemory();
        double result = calculator.multiplication(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 3", "3, 0.5, 2.5", "0.9, 3, -2.1", "-3, 3, -6"})
    public void testsMinusNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        CalculatorWithMemory calculator = new CalculatorWithMemory();
        double result = calculator.minus(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 9", "3, 0.5, 3.5", "0.9, 3, 3.9", "-3, 3, 0"})
    public void testsAddingNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        CalculatorWithMemory calculator = new CalculatorWithMemory();
        double result = calculator.adding(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSave() {
        CalculatorWithMemory calculator = new CalculatorWithMemory();
        calculator.adding(2, 2);
        calculator.save();
        Assertions.assertEquals(4, calculator.load());
    }

    @Test
    public void testLoadWithoutSave() {
        CalculatorWithMemory calculator = new CalculatorWithMemory();
        calculator.adding(2, 2);
        Assertions.assertEquals(0, calculator.load());
    }

}
