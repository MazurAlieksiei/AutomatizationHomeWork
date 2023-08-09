package org.calcstask;


public class CalculatorWithMemory {

    private double lastOperationResult;

    private double savedOperationResult;


    /**
     * Метод деления чисел.
     *
     * @param firstNumber  Число, которое необходимо поделить (левый операнд).
     * @param secondNumber Число, на которое необходимо поделить (правый операнд).
     * @return Возвращает результат деления чисел.
     */
    public double division(double firstNumber, double secondNumber) {
        if (secondNumber == 0) {
            throw new ArithmeticException("You can't divide by zero!");
        }
        lastOperationResult = firstNumber / secondNumber;
        return lastOperationResult;
    }

    /**
     * Метод умножения чисел.
     *
     * @param firstNumber  Число, которе необходимо умножить (левый операнд).
     * @param secondNumber Числи, на которое необходимо умножить (правый операнд).
     * @return Возвращает результат умножения чисел.
     */
    public double multiplication(double firstNumber, double secondNumber) {
        lastOperationResult = firstNumber * secondNumber;
        return lastOperationResult;
    }

    /**
     * Метод вычитания чисел.
     *
     * @param firstNumber  Число, из которого вычитается (левый операнд).
     * @param secondNumber Число, которое вычитают (правый операнд).
     * @return Возвращает результат вычитания левого числа от правого.
     */
    public double minus(double firstNumber, double secondNumber) {
        lastOperationResult = firstNumber - secondNumber;
        return lastOperationResult;
    }

    /**
     * Метод сложения чисел.
     *
     * @param firstNumber  Число, к которому прибавляется (левый операнд).
     * @param secondNumber Число, которое прибавляется (правый операнд).
     * @return Возвращает результат сложения чисел.
     */
    public double adding(double firstNumber, double secondNumber) {
        lastOperationResult = firstNumber + secondNumber;
        return lastOperationResult;
    }

    /**
     * Метод сохранения результата вычислений.
     */
    public void save() {
        savedOperationResult = lastOperationResult;
    }

    /**
     * Метод получения сохраненного результата вычислений. После получения, результат обнуляется.
     *
     * @return Возвращает сохраненный результат вычислений.
     */
    public double load() {
        double temp = savedOperationResult;
        savedOperationResult = 0;
        return temp;
    }
}
