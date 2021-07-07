package com.epam.web.utils;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoaderTest {

    private final static String VALID_FILE_PROPERTIES = "test.properties";
    private final static String INVALID_FILE_PROPERTIES = "invalid.properties";
    private final static String VALID_PROPERTY = "test.password";
    private final static String INVALID_PROPERTY = "invalid.password";
    private final static PropertiesLoader LOADER = new PropertiesLoader();

    @Test
    public void testShouldLoadPropertiesWhenValidFileGiven() throws IOException {
        //given
        Properties properties = LOADER.loadProperties(VALID_FILE_PROPERTIES);
        String expected = "password";

        //when
        String actual = properties.getProperty(VALID_PROPERTY);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IOException.class)
    public void testShouldThrowsIOExceptionWhenInvalidFileGiven() throws IOException {
        //when
        Properties properties = LOADER.loadProperties(INVALID_FILE_PROPERTIES);
    }

    @Test
    public void testShouldReturnNullWhenTryLoadingValidPropertyFromValidFile() throws IOException {
        //given
        Properties properties = LOADER.loadProperties(VALID_FILE_PROPERTIES);

        //when
        String actual = properties.getProperty(INVALID_PROPERTY);

        //then
        Assert.assertNull(actual);
    }

}
