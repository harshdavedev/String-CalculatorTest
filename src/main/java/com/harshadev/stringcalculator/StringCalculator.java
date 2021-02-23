/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harshadev.stringcalculator;

/**
 * A simple String Calculator.
 */
public class StringCalculator {

    private static final int UPPER_BOUNDS = 1000;

    /**
     * Calculates a sum of a delimited string of numbers.
     * <p>
     *
     * @param input either "" or a delimited set of numbers.
     * @return 0 or the sum of the delimited numbers.
     */
    public int Add(String input) {
        if (input.isEmpty()) {
            return 0;
        }
        NumberDelimiterParser parser = new NumberDelimiterParser();

        StringBuilder negativeNumbersErrorMessage = new StringBuilder();
        int sum = 0;
        for (Integer number : parser.getNumbers(input)) {
            if (isNumberValid(number)) {
                sum += number;
            } else {
                if (isNegativeNumber(number)) {
                    if (negativeNumbersErrorMessage.length() == 0) {
                        negativeNumbersErrorMessage.append("Negative numbers not allowed. Input contains: ");
                    }
                    negativeNumbersErrorMessage.append(number);
                    negativeNumbersErrorMessage.append(", ");
                }
            }
        }
        if (negativeNumbersErrorMessage.length() != 0) {
            negativeNumbersErrorMessage.setLength(negativeNumbersErrorMessage.length() - 2);
            throw new IllegalArgumentException(negativeNumbersErrorMessage.toString());
        }
        return sum;
    }

    private boolean isNumberValid(Integer number) {
        return !isNegativeNumber(number) && !isNumberTooBig(number);
    }

    private boolean isNegativeNumber(Integer number) {
        return number < 0;
    }

    private boolean isNumberTooBig(Integer number) {
        return number > UPPER_BOUNDS;
    }
}
