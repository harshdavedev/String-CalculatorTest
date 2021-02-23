package com.harshadev.stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple number parser.
 */
public class NumberDelimiterParser {

    private static final String DEFAULT_DELIMITER_PATTERN = "[,\\n]";
    private static final String CUSTOM_DELIMITER_PATTERN = "^//((.)|\\[(.*)\\])\\n(.*)$";
    private static final String MULTIPLE_CUSTOM_DELIMITERS_PATTERN = "\\]\\[";

    private static final int SINGLE_CHAR_CUSTOM_DELIMITER_DELIMITER_GROUP = 1;
    private static final int MULTIPLE_CHAR_CUSTOM_DELIMITER_DELIMITER_GROUP = 3;
    private static final int CUSTOM_DELIMITER_NUMBERS_GROUP = 4;

    private static final String DEFAULT_DELIMITER = ",";

    /**
     * Parses the input and returns an List of Integers.
     * <p>
     *
     * @param input either "" or a delimited set of numbers.
     * @return a parsed List of Integers from the input.
     */
    public List<Integer> getNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : tokenise(input)) {
            numbers.add(Integer.valueOf(number));
        }
        return numbers;
    }

    /**
     * Parses the input and returns an List of Integers.
     * <p>
     * @param input either "" or a delimited set of numbers.
     * @return
     */

    public List<Integer> getNumbersOtherSimpleRegex(String input) {
        List<Integer> numbers = new ArrayList<>();
        String numberString[]=input.split("[^\\w]");
        List<String> numberlist = new ArrayList<String>(Arrays.asList(numberString));
        numberlist.removeAll(Arrays.asList("", null));
        for (String number : numberlist) {
            numbers.add(Integer.valueOf(number));
        }
        return numbers;
    }

    private String[] tokenise(String input) {
        String numbers = parseNumbers(input);
        return numbers.split(DEFAULT_DELIMITER);
    }

    private String parseNumbers(String input) {

        if (!input.startsWith("//")) {
            return input.replaceAll(DEFAULT_DELIMITER_PATTERN, DEFAULT_DELIMITER);
        }

        Matcher delimiterMatcher = Pattern.compile(CUSTOM_DELIMITER_PATTERN).matcher(input);
        if (!delimiterMatcher.matches()) {
            throw new IllegalArgumentException("Unable to parse input: " + input);
        }
        String delimiters = getDelimiters(delimiterMatcher);

        String numbers = delimiterMatcher.group(CUSTOM_DELIMITER_NUMBERS_GROUP);
        for (String delimiter : delimiters.split(MULTIPLE_CUSTOM_DELIMITERS_PATTERN)) {
            numbers = numbers.replace(delimiter, DEFAULT_DELIMITER);
        }
        return numbers;
    }

    private String getDelimiters(Matcher delimiterMatcher) {
        if (delimiterMatcher.group(MULTIPLE_CHAR_CUSTOM_DELIMITER_DELIMITER_GROUP) != null) {
            return delimiterMatcher.group(MULTIPLE_CHAR_CUSTOM_DELIMITER_DELIMITER_GROUP);
        } else {
            return delimiterMatcher.group(SINGLE_CHAR_CUSTOM_DELIMITER_DELIMITER_GROUP);
        }
    }
}
