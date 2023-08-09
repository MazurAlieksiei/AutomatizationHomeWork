package org.atmtask.cards;

public class DebitCard extends Card {

    public DebitCard(String cardHolderName, long balance) {
        super(cardHolderName, balance);
    }

    public DebitCard(String cardHolderName) {
        super(cardHolderName);
    }

    /**
     * Метод уменьшения баланса.
     *
     * @param quantity Число, на которое уменьшается баланс.
     * @return Возвращает "Not allowed", если quantity превышает значения баланса.
     * Возвращает баланс, уменьшенный на quantity.
     */
    @Override
    public String decreaseBalance(long quantity) {
        if ((getBalance() - quantity) < 0) {
            return "Not allowed";
        }
        return super.decreaseBalance(quantity);
    }
}
