package com.epam.web.validator;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.OrderStatus;
import com.epam.web.entitiy.RoomClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderValidatorTest {

    private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);

    private final static String VALID_NAME = "Rehanna";
    private final static String INVALID_NAME = "123cdsv4234";
    private final static int VALID_PLACE_COUNT = 2;
    private final static int INVALID_PLACE_COUNT = -2;
    private final static int ZERO_PLACE_COUNT = 0;

    private static OrderValidator validator;

    @BeforeClass
    public static void init() {
        validator = new OrderValidator();
    }

    @Test
    public void testShouldReturnTrueWhenCorrectOrderApplied() throws ParseException {
        //given
        Order order = new Order(0, 0, VALID_NAME,
                                RoomClass.URBAN.toString(),
                                VALID_PLACE_COUNT, DATE_FORMAT.parse("2021-10-15"),
                                DATE_FORMAT.parse("2021-10-25"),
                                OrderStatus.PROCESSING);

        //when
        boolean actual = validator.validate(order);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testShouldReturnFalseWhenInvalidNameApplied() throws ParseException {
        //given
        Order order = new Order(0, 0, INVALID_NAME,
                RoomClass.URBAN.toString(),
                VALID_PLACE_COUNT, DATE_FORMAT.parse("2021-10-15"),
                DATE_FORMAT.parse("2021-10-25"),
                OrderStatus.PROCESSING);

        //when
        boolean actual = validator.validate(order);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldReturnFalseWhenZeroPlaceCountApplied() throws ParseException {
        //given
        Order order = new Order(0, 0, VALID_NAME,
                RoomClass.URBAN.toString(),
                ZERO_PLACE_COUNT, DATE_FORMAT.parse("2021-10-15"),
                DATE_FORMAT.parse("2021-10-25"),
                OrderStatus.PROCESSING);

        //when
        boolean actual = validator.validate(order);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldReturnFalseWhenInvalidPlaceCountApplied() throws ParseException {
        //given
        Order order = new Order(0, 0, VALID_NAME,
                RoomClass.URBAN.toString(),
                INVALID_PLACE_COUNT, DATE_FORMAT.parse("2021-10-15"),
                DATE_FORMAT.parse("2021-10-25"),
                OrderStatus.PROCESSING);

        //when
        boolean actual = validator.validate(order);

        //then
        Assert.assertFalse(actual);
    }


    @Test
    public void testShouldReturnFalseWhenInvalidDatesApplied() throws ParseException {
        //given
        Order order = new Order(0, 0, VALID_NAME,
                RoomClass.URBAN.toString(),
                VALID_PLACE_COUNT, DATE_FORMAT.parse("2021-10-25"),
                DATE_FORMAT.parse("2021-10-15"),
                OrderStatus.PROCESSING);

        //when
        boolean actual = validator.validate(order);

        //then
        Assert.assertFalse(actual);
    }


    @Test
    public void testShouldReturnFalseWhenPassedDateApplied() throws ParseException {
        //given
        Order order = new Order(0, 0, VALID_NAME,
                RoomClass.URBAN.toString(),
                VALID_PLACE_COUNT, DATE_FORMAT.parse("2021-1-15"),
                DATE_FORMAT.parse("2021-4-25"),
                OrderStatus.PROCESSING);

        //when
        boolean actual = validator.validate(order);

        //then
        Assert.assertFalse(actual);
    }

}
