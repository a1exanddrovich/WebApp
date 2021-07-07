package com.epam.web.validator;

import com.epam.web.entitiy.Room;
import com.epam.web.entitiy.RoomClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoomValidatorTest {

    private static RoomValidator validator;
    private final static String INVALID_CLASS = "invalidClass";

    @BeforeClass
    public static void init() {
        validator = new RoomValidator();
    }

    @Test
    public void testShouldReturnTrueWhenValidRoomApplied() {
        //given
        Room validRoom = new Room(0, 2, RoomClass.THEMED, 2, null, null);

        //when
        boolean actual = validator.validate(validRoom);

        //then
        Assert.assertTrue(actual);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testShouldReturnFalseWhenRoomWithInvalidClassApplied() {
        //given
        Room validRoom = new Room(0, 2, RoomClass.valueOf(INVALID_CLASS), 0, null, null);
    }

    @Test
    public void testShouldReturnFalseWhenRoomWithZeroPlaceCountApplied() {
        //given
        Room validRoom = new Room(0, 2, RoomClass.BEACH, 0, null, null);

        //when
        boolean actual = validator.validate(validRoom);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldReturnFalseWhenRoomWithNegativePlaceCountApplied() {
        //given
        Room validRoom = new Room(0, 2, RoomClass.SKI, -2, null, null);

        //when
        boolean actual = validator.validate(validRoom);

        //then
        Assert.assertFalse(actual);
    }

}
