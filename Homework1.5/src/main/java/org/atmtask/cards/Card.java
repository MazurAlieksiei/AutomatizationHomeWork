package org.atmtask.cards;

import org.atmtask.exeption.CashWithdrawalException;

public abstract class Card {

    private String cardHolderName;

    private long balance;

    public Card(String cardHolderName, long balance) {
        this.cardHolderName = cardHolderName;
        this.balance = balance;
    }

    public Card(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    /**
     * Метод получения баланса.
     */
    public long getBalance() {
        return this.balance;
    }

    /**
     * Метод увеличения баланса.
     *
     * @param quantity Число, на которое увеличивается баланс.
     */
    public long increaseBalance(long quantity) {
        return this.balance += quantity;
    }

    /**
     * Метод уменьшения баланса.
     *
     * @param quantity Число, на которое уменьшается баланс.
     */
    public String decreaseBalance(long quantity) throws CashWithdrawalException {
        return String.valueOf(this.balance -= quantity);
    }

    /**
     * Метод конвертации баланса в выбранную валюту по заданному курсу.
     *
     * @param exchangeRate Курс конвертации.
     * @param currency     Валюта, в которую конвертируют.
     * @return Возвращает строку с балансом в заданной валюте.
     */
    public String getBalanceInDifferentCurrency(double exchangeRate, String currency) {
        double balanceInDifferentCurrency = this.balance / exchangeRate;
        String roundValue = String.format("%.2f", balanceInDifferentCurrency);
        return roundValue + " " + currency;
    }
}
