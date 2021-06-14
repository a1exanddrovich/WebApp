package com.epam.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private final static String FILE_NOT_FOUND = "File not found";

    public Properties loadProperties(String fileName) throws IOException {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {

            if(inputStream == null) {
                throw new IOException(FILE_NOT_FOUND);
            }

            Properties properties = new Properties();
            properties.load(inputStream);

            return properties;
        }
    }

}
