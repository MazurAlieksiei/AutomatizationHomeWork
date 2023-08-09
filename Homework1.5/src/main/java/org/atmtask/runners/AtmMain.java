package org.atmtask.runners;


import org.atmtask.Atm;
import org.atmtask.cards.Card;
import org.atmtask.cards.DebitCard;

import java.util.Scanner;

public class AtmMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Card card = new DebitCard("Lena", 2000);
        Atm atm = new Atm();

        atm.insertCard(card);

        do {

            System.out.println("Please, enter operation to do (to exit, please, push Enter): " +
                    "getBalance, withdrawal, refill?\nIn order to get balance in different currency, please, " +
                    "enter \"dif\".");

            String operationChoice = in.nextLine();

            if (operationChoice.isBlank()) {
                return;
            }

            switch (operationChoice) {
                case "getBalance":
                    System.out.println("Current Account balance: " + atm.getBalance());
                    break;
                case "withdrawal":
                    System.out.println("Enter amount to withdrawal: ");

                    long amountToWithdrawal = 0;

                    try {
                        amountToWithdrawal = Long.parseLong(in.nextLine());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    if (card instanceof DebitCard) {
                        atm.withdrawal(amountToWithdrawal);
                        System.out.println("Current Account balance: " + atm.getBalance());
                    } else {
                        atm.withdrawal(amountToWithdrawal);
                        System.out.println("Current Account balance: " + atm.getBalance());
                    }
                    break;
                case "refill":
                    System.out.println("Enter amount to refill: ");

                    long amountToRefill = 0;

                    try {
                        amountToRefill = Long.parseLong(in.nextLine());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    atm.refill(amountToRefill);

                    System.out.println("Current Account balance: " + atm.getBalance());
                    break;
                case "dif":
                    System.out.println("Please enter currency: ");
                    String currency = in.nextLine();

                    System.out.println("Please enter exchange rate: ");

                    double exchangeRate = 0;

                    try {
                        exchangeRate = Double.parseDouble(in.nextLine());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Your balance is: " + card.getBalanceInDifferentCurrency(exchangeRate, currency));
                    break;
                default:
                    System.out.println("Invalid operation entered. Please, try again!");
            }
        } while (true);
    }
}
