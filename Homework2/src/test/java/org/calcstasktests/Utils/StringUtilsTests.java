package org.calcstasktests.Utils;

import org.calcstask.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

public class StringUtilsTests {

    private static Stream<Arguments> numberAsStringDataProvider() {
        return Stream.of(
                Arguments.of("6.1", true),
                Arguments.of("9.9999", true),
                Arguments.of("0.9", true),
                Arguments.of("8", true),
                Arguments.of("103", true),
                Arguments.of("0", true),
                Arguments.of("6e1", false),
                Arguments.of("e5", false),
                Arguments.of("a", false),
                Arguments.of("5t", false),
                Arguments.of("12..12", false),
                Arguments.of("a", false),
                Arguments.of("-3", true),
                Arguments.of("3-3", false),
                Arguments.of("-3-", false),
                Arguments.of("a", false));
    }

    @ParameterizedTest
    @MethodSource("numberAsStringDataProvider")
    public void isInputNumberCorrectWrongInputWithLetters(String consoleInput, boolean expected) {
        boolean result = StringUtils.isInputNumberCorrect(consoleInput);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @NullSource
    public void isInputNumberCorrectsNullInput(String consoleInput) {
        boolean result = StringUtils.isInputNumberCorrect(consoleInput);
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "12.7, 12.7", "103883.6, 103883.6"})
    public void passingOfInputStringsCorrectInputReturnValidResult(String numberAsString, double expected) {
        double result = StringUtils.parsingOfInputStrings(numberAsString);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"1s", "One", "2/0"})
    public void passingOfInputStringsWrongInputThrowsExpectedException(String numberAsString) {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            StringUtils.parsingOfInputStrings(numberAsString);
        });
    }
}
