package com.epam.web.validator;

import com.epam.web.entitiy.User;
import com.epam.web.entitiy.UserRole;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class LoginValidatorTest {

    private final static String VALID_LOGIN = "validLogin";
    private final static String INVALID_LOGIN = "too long login too long login too long login too long login too long login too long login too long login too long login too long login";
    private final static String INVALID_PASSWORD = "too long password too long password too long password too long password too long password too long password too long password too long password too long password";
    private final static String VALID_PASSWORD = "validPassword";

    private static LoginValidator validator;

    @BeforeClass
    public static void init() {
        validator = new LoginValidator();
    }

    @Test
    public void testShouldReturnTrueWhenValidUserApplied() {
        //given
        User user = new User(0, VALID_LOGIN, VALID_PASSWORD, new BigDecimal(0), UserRole.USER, false);

        //when
        boolean actual = validator.validate(user);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testShouldReturnFalseWhenInvalidLoginApplied() {
        //given
        User user = new User(0, INVALID_LOGIN, VALID_PASSWORD, new BigDecimal(0), UserRole.USER, false);

        //when
        boolean actual = validator.validate(user);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldReturnFalseWhenInvalidPasswordApplied() {
        //given
        User user = new User(0, VALID_LOGIN, INVALID_PASSWORD, new BigDecimal(0), UserRole.USER, false);

        //when
        boolean actual = validator.validate(user);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldReturnFalseWhenInvalidLoginAndInvalidPasswordApplied() {
        //given
        User user = new User(0, INVALID_LOGIN, INVALID_PASSWORD, new BigDecimal(0), UserRole.USER, false);

        //when
        boolean actual = validator.validate(user);

        //then
        Assert.assertFalse(actual);
    }

}
