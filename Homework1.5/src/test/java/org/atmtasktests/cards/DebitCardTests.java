package org.atmtasktests.cards;

import org.atmtask.cards.Card;
import org.atmtask.cards.DebitCard;
import org.atmtask.exeption.CashWithdrawalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class DebitCardTests {

    //TODO добавить тесты всех методов, а не только с измененной реализацией. Если нужно будет.
    @ParameterizedTest
    @CsvSource({"1500, 500"})
    public void testDecreaseBalance(long quantity, String expected) {
        Card card = new DebitCard("Alex", 2000);
        var result = Assertions.assertDoesNotThrow(() -> card.decreaseBalance(quantity));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testDecreaseBalanceMoreThenAvailableThrowsExceptionDebitCard() {
        Card card = new DebitCard("Alex", 2000);
        Assertions.assertThrows(CashWithdrawalException.class, () -> card.decreaseBalance(2001));
    }
}
