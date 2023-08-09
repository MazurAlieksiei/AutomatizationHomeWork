package org.calctask.runners;


import org.calctask.CalculatorWithMemory;
import org.calctask.Utils.StringUtils;

import java.util.Scanner;

public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorWithMemory calculator = new CalculatorWithMemory();
        Scanner in = new Scanner(System.in);

        do {

            System.out.println("Please, enter first number (to exit, please, push Enter):  ");
            String firstNumberAsString = in.nextLine();

            if (firstNumberAsString.isBlank()) {
                return;
            }

            if (!StringUtils.isInputNumberCorrect(firstNumberAsString)) {
                System.out.println("The entered data is not a number =(. Please, try again!");
                continue;
            }

            System.out.println("Please, enter second number: ");
            String secondNumberAsString = in.nextLine();

            if (!StringUtils.isInputNumberCorrect(secondNumberAsString)) {
                System.out.println("The entered data is not a number. Please, try again!");
                continue;
            }

            System.out.println("What operation need to be done? Please, enter div, multi, minus or add.");

            String operationToDo = in.nextLine();
            double firstNumber = Double.parseDouble(firstNumberAsString);
            double secondNumber = Double.parseDouble(secondNumberAsString);

            switch (operationToDo) {
                case "div":
                    try {
                        System.out.println(calculator.division(firstNumber, secondNumber));
                    } catch (ArithmeticException e) {
                        System.out.println(e.getMessage());
                    }
                    calculator.save();
                    break;
                case "multi":
                    System.out.println(calculator.multiplication(firstNumber, secondNumber));
                    calculator.save();
                    break;
                case "minus":
                    System.out.println(calculator.minus(firstNumber, secondNumber));
                    calculator.save();
                    break;
                case "add":
                    System.out.println(calculator.adding(firstNumber, secondNumber));
                    calculator.save();
                    break;
                default:
                    System.out.println("Invalid operation entered =(. Please, try again!");
            }

        } while (true);
    }
}
