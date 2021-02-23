package com.harshadev.stringcalculator;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * A Test for the simple String Calculator.
 */
public class StringCalculatorTest {

    private static final Logger logger = Logger.getLogger(StringCalculatorTest.class.getName());

    private StringCalculator instance;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void createCalculator() {
        instance = new StringCalculator();
    }

    /**
     * Tests that  numbers separted by comma returns their sum.
     */

    @Test
    public void testNumbersSepartedByComma() {
        logger.log(Level.FINER, "testNumbersSepartedByComma");

        String numbers = "1,2";
        int expResult = 3;

        int result = instance.Add(numbers);

        assertEquals(expResult, result);
    }


    /**
     * Tests that passing an empty string returns 0.
     */
    @Test
    public void testAddEmptyString() {
        logger.log(Level.FINER, "testAddEmptyStringZero");

        String numbers = "";
        int expResult = 0;

        int result = instance.Add(numbers);

        assertEquals(expResult, result);
    }

    /**
     * Tests that passing one number returns that number.
     */
    @Test
    public void testAddOneNumber() {
        logger.log(Level.FINER, "testAddOneNumber");

        String numbers = "1";
        int expResult = 1;

        int result = instance.Add(numbers);

        assertEquals(expResult, result);
    }

    /**
     * Tests that passing 3 numbers returns their sum.
     */
    @Test
    public void testAddThreeDifferentNumbers() {
        logger.log(Level.FINER, "testAddThreeDifferentNumbers");

        String numbers = "1,2,5";
        int expResult = 8;

        int result = instance.Add(numbers);

        assertEquals(expResult, result);
    }

    /**
     * Test that when passing the biggest number allowed that number is added.
     */
    @Test
    public void testBiggestNumberIsAdded() {
        logger.log(Level.FINER, "testBigNumberIsIgnored");

        String numbers = "1,1000";
        int expResult = 1001;

        int result = instance.Add(numbers);

        assertEquals(expResult, result);
    }

    /**
     * Test that when passing a big number that number is ignored.
     */
    @Test
    public void testBigNumberIsIgnored() {
        logger.log(Level.FINER, "testBigNumberIsIgnored");

        String numbers = "2,1001";
        int expResult = 2;

        int result = instance.Add(numbers);

        assertEquals(expResult, result);
    }

    /**
     * Tests that passing a negative number throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumber() {
        logger.log(Level.FINER, "testNegativeNumber");

        String numbers = "-1";
        instance.Add(numbers);
    }

    /**
     * Tests that the negative number appears in the exception message.
     */
    @Test
    public void testNegativeNumberErrorMessage() {
        logger.log(Level.FINER, "testNegativeNumberErrorMessage");

        String numbers = "-1";

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(StringContains.containsString("-1"));

        instance.Add(numbers);
    }

    /**
     * Tests that the negative number appears in the exception message.
     */
    @Test
    public void testNegativeNumberErrorMessage2Numbers() {
        logger.log(Level.FINER, "testNegativeNumberErrorMessage2Numbers");

        String numbers = "-1,-2";

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(StringContains.containsString("-1, -2"));

        instance.Add(numbers);
    }
}
