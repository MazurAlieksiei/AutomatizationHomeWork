package org.atmtasktests;


import org.atmtask.Atm;
import org.atmtask.cards.Card;
import org.atmtask.cards.CreditCard;
import org.atmtask.cards.DebitCard;
import org.atmtask.exeption.CashWithdrawalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AtmTests {

    @Test
    public void testGetBalance() {
        Card card = new DebitCard("Lena");
        Atm atm = new Atm();
        atm.insertCard(card);
        Assertions.assertEquals(0, atm.getBalance());
    }

    @Test
    public void testRefill() {
        Card card = new DebitCard("Lena");
        Atm atm = new Atm();
        atm.insertCard(card);
        atm.refill(1000);
        Assertions.assertEquals(1000, atm.getBalance());
    }

    @Test
    public void testWithdrawalDebitCard() {
        Card card1 = new DebitCard("Lena", 1000);
        Atm atm = new Atm();
        atm.insertCard(card1);
        var result = Assertions.assertDoesNotThrow(() -> atm.withdrawal(900));
        Assertions.assertEquals("100", result);
    }

    @Test
    public void testWithdrawalMoreThenAvailableThrowsExceptionDebitCard() {
        Card card1 = new DebitCard("Lena", 1000);
        Atm atm = new Atm();
        atm.insertCard(card1);
        Assertions.assertThrows(CashWithdrawalException.class, () -> atm.withdrawal(1001));
    }

    @Test
    public void testWithdrawalCreditCard() {
        Card card1 = new CreditCard("Lena", 1000);
        Atm atm = new Atm();
        atm.insertCard(card1);
        var result = Assertions.assertDoesNotThrow(() -> atm.withdrawal(900));
        Assertions.assertEquals("100", result);
    }

    @Test
    public void testWithdrawalMoreThenAvailableCreditCard() {
        Card card1 = new CreditCard("Lena", 1000);
        Atm atm = new Atm();
        atm.insertCard(card1);
        var result = Assertions.assertDoesNotThrow(() -> atm.withdrawal(1001));
        Assertions.assertEquals("-1", result);
    }
}

