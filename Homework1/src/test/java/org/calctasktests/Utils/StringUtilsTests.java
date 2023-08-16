package org.calctasktests.Utils;

import org.calctask.Utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringUtilsTests {
    @Test
    public void isInputNumberCorrectsNullInput() {
        String consoleInput = null;
        boolean result = StringUtils.isInputNumberCorrect(consoleInput);
        Assertions.assertFalse(result);
    }


    @ParameterizedTest
    @CsvSource({"6.1, true", "9.9999, true", "0.9,true"})
    public void isInputNumberCorrectInputDoubleValue(String consoleInput, boolean expected) {
        boolean result = StringUtils.isInputNumberCorrect(consoleInput);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"6e1, false", "e5, false", "a, false", " 5t, false", "12..12, false"})
    public void isInputNumberCorrectInputWithLetters(String consoleInput, boolean expected) {
        boolean result = StringUtils.isInputNumberCorrect(consoleInput);
        Assertions.assertEquals(expected, result);
    }
}
