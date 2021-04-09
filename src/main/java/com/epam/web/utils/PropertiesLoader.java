package com.epam.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public Properties loadProperties(String fileName) throws IOException {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if(inputStream == null) {
                throw new IOException("File not found");
            }
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        }
    }

}
