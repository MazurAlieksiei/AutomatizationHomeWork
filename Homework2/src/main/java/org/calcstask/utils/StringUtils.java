package org.calcstask.utils;

public class StringUtils {

    /**
     * Метод проверки введенного числа.
     *
     * @param consoleInput Введенное пользователем число.
     * @return Возвращает true, если ввод является корректным числом, false, если ввод не является корректным числом.
     */
    public static boolean isInputNumberCorrect(String consoleInput) {
        if (consoleInput == null) {
            return false;
        }
        if (!consoleInput.chars().allMatch(Character::isDigit)) {
            if (consoleInput.chars().filter(ch -> ch == '.').count() == 1 ||
                    (consoleInput.startsWith("-") && consoleInput.chars().filter(ch -> ch == '-').count() == 1)) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * Метод парсинга введенных пользователем строк.
     *
     * @param numberAsString Число, введенное пользователем как строка.
     * @return Возвращает число типа double.
     */
    public static double parsingOfInputStrings(String numberAsString) {
        return Double.parseDouble(numberAsString);
    }
}
