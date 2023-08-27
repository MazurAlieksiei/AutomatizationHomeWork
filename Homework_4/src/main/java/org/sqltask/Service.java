package org.sqltask;

import org.sqltask.db.DbHelper;
import org.sqltask.models.Account;
import org.sqltask.models.Transaction;
import org.sqltask.models.User;

import java.security.InvalidParameterException;

public class Service {

    private DbHelper helper;
    public Service(DbHelper helper) {
        this.helper = helper;
    }

    /**
     * Метод добавления нового пользователя, если его нет.
     * @param name Имя пользователя.
     * @param address Адрес пользователя.
     * @return Возвращает модель пользователя.
     */
    public User addUser(String name, String address) {
        if (name == null || name.isBlank()) {
            throw new InvalidParameterException("User name cannot be empty!");
        }

        User user = address.isBlank() || address == null ? helper.getUser(name) : helper.getUser(name, address);
        if (user == null) {
            helper.addUser(new User(name, address));
            user = helper.getUser(name, address);
        }
        return user;
    }

    public Account addNewAccountForUser(String currency, int userId) {
        if (currency == null || currency.isBlank()) {
            throw new InvalidParameterException("Account currency cannot be empty!");
        }

        Account account = helper.getAccount(userId, currency);
        if (account != null) {
            throw new InvalidParameterException("User cannot have more then one account in this currency" + currency +"!");

        }

        helper.addAccount(new Account(0, currency, userId));
        return helper.getAccount(userId, currency);
    }

    public Account getExistingAccount(int userId, String currency) {
        if (currency == null || currency.isBlank()) {
            throw new InvalidParameterException("Account currency cannot be empty!");
        }

        Account account = helper.getAccount(userId, currency);
        if (account == null) {
            throw new InvalidParameterException("Such account not exist.");

        }

        return account;
    }
    public Account refillAccount(double amount, int accountId) {
        Account account = helper.getAccount(accountId);
        if (account == null) {
            throw new InvalidParameterException("Account with this id (" + accountId + ") not exists!");
        }

        if (amount > 100000000) {
            throw new InvalidParameterException("Transaction amount cannot be more then 100000000.");
        }

        if (account.getBalance() < 0 || account.getBalance() + amount > 2000000000) {
            throw new InvalidParameterException("Replenishment is impossible. The balance limit has been exceeded.");
        }

        helper.accountUpdate(accountId, account.getBalance() + amount);
        helper.addTransaction(new Transaction(amount, accountId));

        return helper.getAccount(accountId);
    }

    public Account withdrawalFromAccount(double amount, int accountId) {
        Account account = helper.getAccount(accountId);
        if (account == null) {
            throw new InvalidParameterException("Account with this id (" + accountId + ") not exists!");
        }

        if (amount > 100000000) {
            throw new InvalidParameterException("Transaction amount cannot be more then 100000000.");
        }

        if (account.getBalance() == 0 || account.getBalance() - amount < 0) {
            throw new InvalidParameterException("Withdrawal is impossible. The balance cannot be less then zero.");
        }

        helper.accountUpdate(accountId, account.getBalance() - amount);
        helper.addTransaction(new Transaction(-amount, accountId));

        return helper.getAccount(accountId);
    }
}
