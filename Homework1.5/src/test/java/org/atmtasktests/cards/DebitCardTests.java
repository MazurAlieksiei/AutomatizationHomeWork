package org.atmtasktests.cards;

import org.atmtask.cards.Card;
import org.atmtask.cards.DebitCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DebitCardTests {

    //TODO добавить тесты всех методов, а не только с измененной реализацией. Если нужно будет.
    @ParameterizedTest
    @CsvSource({"1500, 500", "2001, Not allowed"})
    public void testDecreaseBalance(long quantity, String expected) {
        Card card = new DebitCard("Alex", 2000);
        Assertions.assertEquals(expected, card.decreaseBalance(quantity));
    }
}
