package com.epam.web.validator;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.math.BigDecimal;

public class CashValidatorTest {

    private final static String VALID_NUMBER = "120.5";
    private final static int NULL_NUMBER = 0;
    private final static String INVALID_NUMBER = "-56.89";

    private static CashValidator validator;

    @BeforeClass
    public static void init() {
        validator = new CashValidator();
    }

    @Test
    public void testShouldReturnTrueWhenCorrectCashApplied() {
        //given
        BigDecimal cash = new BigDecimal(VALID_NUMBER);

        //when
        boolean actual = validator.validate(cash);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testShouldReturnFalseWhenNullApplied() {
        //given
        BigDecimal cash = new BigDecimal(NULL_NUMBER);

        //when
        boolean actual = validator.validate(cash);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldReturnFalseWhenNegativeNumberApplied() {
        //given
        BigDecimal cash = new BigDecimal(INVALID_NUMBER);

        //when
        boolean actual = validator.validate(cash);

        //then
        Assert.assertFalse(actual);
    }

}
