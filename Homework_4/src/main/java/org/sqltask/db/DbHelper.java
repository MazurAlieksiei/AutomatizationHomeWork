package org.sqltask.db;

import org.sqltask.models.Account;
import org.sqltask.models.Transaction;
import org.sqltask.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.sqltask.utils.FileUtils.readFromFile;

public class DbHelper {

    private Statement statement;

    /**
     * Метод создания таблиц в базе данных.
      */
    public void createTables() {
        try {
            statement = DbProvider.getConnection().createStatement();
            statement.executeUpdate(readFromFile("tables.sql"));

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {

            try {

                if(statement!=null){
                    statement.close();
                }

            } catch (SQLException e) {
                System.out.println("Cant close connection");
            }
        }
    }



    public void addUser(User user) {
        try (PreparedStatement statement = DbProvider.getConnection().prepareStatement((
                "INSERT INTO Users(name, address) VALUES(?, ?)"))) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public User getUser(String name, String address) {
        User user = null;
        try (PreparedStatement statement = DbProvider.getConnection().prepareStatement((
                "SELECT * FROM Users WHERE name = \"" + name + "\" AND address = \"" + address +"\""))) {
            ResultSet resultSet =statement.executeQuery();
            int id = resultSet.getInt("userId");

            if (id == 0){
                return null;
            }

             String userName = resultSet.getString("name");
            String userAddress = resultSet.getString("address");
            user = new User(id, userName, userAddress);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }

    public User getUser(String name) {
        User user = null;
        try (PreparedStatement statement = DbProvider.getConnection().prepareStatement((
                "SELECT * FROM Users WHERE name = \"" + name +"\""))) {
            ResultSet resultSet =statement.executeQuery();
            int id = resultSet.getInt("userId");

            if (id == 0){
                return null;
            }

            String userName = resultSet.getString("name");
            String userAddress = resultSet.getString("address");
            user = new User(id, userName, userAddress);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }

    public void addAccount(Account account) {
        try (PreparedStatement statement = DbProvider.getConnection().prepareStatement((
                "INSERT INTO Accounts(userId, balance, currency) VALUES(?, ?, ?)"))) {
            statement.setInt(1, account.getUserId());
            statement.setDouble(2, account.getBalance());
            statement.setString(3, account.getCurrency());
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public Account getAccount(int userId, String currency) {
        Account account = null;
        try (PreparedStatement statement = DbProvider.getConnection().prepareStatement((
                "SELECT * FROM Accounts WHERE userId = " + userId + " AND currency = \"" + currency +"\""))) {
            ResultSet resultSet =statement.executeQuery();
            int accountId = resultSet.getInt("accountId");

            if (accountId == 0){
                return null;
            }

            int accountUserId = resultSet.getInt("userId");
            double accountBalance = resultSet.getDouble("balance");
            String accountCurrency = resultSet.getString("currency");
            account = new Account(accountId, accountUserId, accountBalance, accountCurrency);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return account;
    }

    public Account getAccount(int accountId) {
        Account account = null;
        try (PreparedStatement statement = DbProvider.getConnection().prepareStatement((
                "SELECT * FROM Accounts WHERE accountId = " + accountId))) {
            ResultSet resultSet =statement.executeQuery();
            int id = resultSet.getInt("accountId");

            if (id == 0){
                return null;
            }

            int accountUserId = resultSet.getInt("userId");
            double accountBalance = resultSet.getDouble("balance");
            String accountCurrency = resultSet.getString("currency");
            account = new Account(id, accountUserId, accountBalance, accountCurrency);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return account;
    }

    /**
     * Метод обновления данных столбца balance в таблице Accounts.
     * @param accountId Id аккаунта.
     * @param balance Баланс аккаунта.
     */
    public void accountUpdate(int accountId, double balance) {
        try (Statement statement = DbProvider.getConnection().createStatement()) {
            String sql = "UPDATE Accounts SET balance = " + balance + " WHERE accountId = " + accountId;
            statement.executeUpdate(sql);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    /**
     * Метод добавления данных в таблицу Transactions.
     *
     * @param transaction Модель транзакций.
     */
    public void addTransaction(Transaction transaction) {
        try (PreparedStatement statement = DbProvider.getConnection().prepareStatement((
                "INSERT INTO Transactions(accountId, amount) VALUES(?, ?)"))) {
            statement.setInt(1, transaction.getAccountId());
            statement.setDouble(2, transaction.getAmount());
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
