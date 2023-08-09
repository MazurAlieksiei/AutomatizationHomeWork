package org.calctasktests;

import org.calctask.CalculatorWithMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorWithMemoryTests {

    CalculatorWithMemory calculator = new CalculatorWithMemory();


    @ParameterizedTest
    @CsvSource({"6, 3, 2", "3, 0.5, 6", "0.9, 3, 0.3", "-3, 3, -1"})
    public void divisionNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        double result = calculator.division(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 18", "3, 0.5, 1.5", "0.9, 3, 2.7", "-3, 3, -9"})
    public void multiplicationNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        double result = calculator.multiplication(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 3", "3, 0.5, 2.5", "0.9, 3, -2.1", "-3, 3, -6"})
    public void minusNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        double result = calculator.minus(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6, 3, 9", "3, 0.5, 3.5", "0.9, 3, 3.9", "-3, 3, 0"})
    public void addingNormalInputReturnsValidResult(double firstNumber, double secondNumber, double expected) {
        double result = calculator.adding(firstNumber, secondNumber);
        Assertions.assertEquals(expected, result);
    }


}
