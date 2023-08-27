package org.sqltask.runners;

import org.sqltask.Service;
import org.sqltask.db.DbHelper;
import org.sqltask.models.Account;
import org.sqltask.models.User;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DbHelper helper = new DbHelper();
        Service service = new Service(helper);

        try {
            helper.createTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Create new user?Yes/NO");
            String userChoice = in.nextLine();

            if (!userChoice.equals("Yes")) {
                System.out.println("Have a nice day! Bye!");
                return;
            }

            System.out.println("Enter user name:");
            String userName = in.nextLine();
            System.out.println("Enter user address:");
            String userAddress = in.nextLine();

            User currentUser = null;
            try {
                currentUser = service.addUser(userName, userAddress);
            } catch (InvalidParameterException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
            while (true) {

                Account currentUserAccount = null;

                System.out.println("Create new account?Yes/NO");
                String accountChoice = in.nextLine();

                if (accountChoice.equals("Yes")) {

                    System.out.println("Enter account currency");
                    String currency = in.nextLine();

                    try {
                        currentUserAccount = service.addNewAccountForUser(currency, currentUser.getUserId());
                    } catch (InvalidParameterException ex) {
                        System.out.println(ex.getMessage());
                        //continue;
                    }

                } else {

                    System.out.println("Want to choose already existed account?Yes/NO");
                    String existedAccountChoice = in.nextLine();

                    if (!existedAccountChoice.equals("Yes")) {
                        System.out.println("Have a nice day! Bye!");
                        return;

                    } else {

                        System.out.println("Enter account currency");
                        String currency = in.nextLine();
                        currentUserAccount = service.getExistingAccount(currentUser.getUserId(), currency);
                    }
                }

                System.out.println("What type of transaction need to be done? (refill, withdrawal)");
                String transactionChoice = in.nextLine();

                System.out.println("Enter amount of transaction:");
                double amount = in.nextDouble();
                in.nextLine();

                try {
                    if (transactionChoice.equals("refill")) {
                        currentUserAccount = service.refillAccount(amount, currentUserAccount.getAccountId());
                    } else if (transactionChoice.equals("withdrawal")) {
                        currentUserAccount = service.withdrawalFromAccount(amount, currentUserAccount.getAccountId());
                    }
                } catch (InvalidParameterException ex) {
                    System.out.println(ex.getMessage());
                    //continue;
                }

                System.out.println("Need to do another operation?Yes/NO");
                String goOnChoice = in.nextLine();
                if (!goOnChoice.equals("Yes")) {
                    return;
                }
            }
        }
    }
}
