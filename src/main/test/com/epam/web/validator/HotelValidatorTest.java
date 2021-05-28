package com.epam.web.validator;

import com.epam.web.entitiy.Hotel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class HotelValidatorTest {

    private final static String VALID_DESCRIPTION = "Some description";
    private final static String VALID_NAME = "Rehanna";
    private final static String INVALID_NAME = "2323text";
    private final static String VALID_IMAGE_NAME = "image.jpg";
    private final static String INVALID_IMAGE_NAME = "imagejpg";
    private final static String TOO_LONG_DESCRIPTION = "very very long description very very long description very very long description very very long description" +
                                                       "very very long description very very long description very very long description very very long description" +
                                                       "very very long description very very long description very very long description very very long description" +
                                                       "very very long description very very long description very very long description very very long description";

    private static HotelValidator validator;

    @BeforeClass
    public static void init() {
        validator = new HotelValidator();
    }

    @Test
    public void testShouldReturnTrueWhenCorrectHotelApplied() {
        //given
        Hotel hotel = new Hotel(0, VALID_NAME, VALID_DESCRIPTION, VALID_IMAGE_NAME, new BigDecimal(0));

        //when
        boolean actual = validator.validate(hotel);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testShouldReturnFalseWhenHotelWithTooLongDescriptionApplied() {
        //given
        Hotel hotel = new Hotel(0, VALID_NAME, TOO_LONG_DESCRIPTION, VALID_IMAGE_NAME, new BigDecimal(0));

        //when
        boolean actual = validator.validate(hotel);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldReturnFalseWhenHotelWithInvalidNameApplied() {
        //given
        Hotel hotel = new Hotel(0, INVALID_NAME, VALID_DESCRIPTION, VALID_IMAGE_NAME, new BigDecimal(0));

        //when
        boolean actual = validator.validate(hotel);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldReturnFalseWhenHotelWithInvalidImageNameApplied() {
        //given
        Hotel hotel = new Hotel(0, INVALID_NAME, VALID_DESCRIPTION, INVALID_IMAGE_NAME, new BigDecimal(0));

        //when
        boolean actual = validator.validate(hotel);

        //then
        Assert.assertFalse(actual);
    }

}
