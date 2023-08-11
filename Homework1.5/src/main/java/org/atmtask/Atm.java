package org.atmtask;


import org.atmtask.cards.Card;
import org.atmtask.exeption.CashWithdrawalException;

public class Atm {

    public Card card;

    public void insertCard(Card card) {
        this.card = card;
    }

    /**
     * Метод снятия со счета.
     *
     * @param amount Сумма, которая снимается со счета.
     * @return Возвращает
     */
    public String withdrawal(long amount) throws CashWithdrawalException {
        return card.decreaseBalance(amount);
    }

    /**
     * Метод пополнения счета.
     *
     * @param amount Сумма, на которую пополняется счет.
     */
    public void refill(long amount) {
        card.increaseBalance(amount);
    }

    /**
     * Метод просмотра баланса счета.
     *
     * @return Возвращает баланс счета.
     */
    public long getBalance() {
        return card.getBalance();
    }
}
