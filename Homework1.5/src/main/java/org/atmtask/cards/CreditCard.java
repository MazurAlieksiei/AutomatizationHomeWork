package org.atmtask.cards;

public class CreditCard extends Card {

    public CreditCard(String cardHolderName, long balance) {
        super(cardHolderName, balance);
    }

    public CreditCard(String cardHolderName) {
        super(cardHolderName);
    }
}
