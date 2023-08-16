package org.atmtasktests.cards;

import org.atmtask.cards.Card;
import org.atmtask.cards.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreditCardTests {

    @Test
    public void testGetBalance() {
        Card card = new CreditCard("Alex");
        Assertions.assertEquals(0, card.getBalance());
    }

    @Test
    public void testIncreaseBalance() {
        Card card = new CreditCard("Alex", 2000);
        Assertions.assertEquals(4000, card.increaseBalance(2000));
    }

    @ParameterizedTest
    @CsvSource({"1500, 500", "2010, -10"})
    public void testDecreaseBalance(long quantity, String expected) {
        Card card = new CreditCard("Alex", 2000);
        var result = Assertions.assertDoesNotThrow(() -> card.decreaseBalance(quantity));
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"3.5, EUR, 571.43 EUR", "3.01, USD, 664.45 USD", "2.47, RUB, 809.72 RUB "})
    public void testGetBalanceInDifferentCurrency(double exchangeRate, String currency, String expected) {
        Card card = new CreditCard("Alex", 2000);
        String result = card.getBalanceInDifferentCurrency(exchangeRate, currency);
        Assertions.assertEquals(expected, result);
    }
}
