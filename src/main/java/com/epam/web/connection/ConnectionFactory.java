package com.epam.web.connection;

import com.epam.web.utils.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private final static String PROPERTIES_FILENAME = "database/database.properties";
    private final static String DATABASE_URL = "database.url";
    private final static String DATABASE_USER = "database.user";
    private final static String DATABASE_PASSWORD = "database.password";
    private final static String DATABASE_DRIVER = "database.driver";

    private String jdbcUrl;
    private String dbUser;
    private String dbPassword;

    /*package-private*/ ConnectionFactory() {
        setUpProperties(PROPERTIES_FILENAME);
    }

    /*package-private*/ Connection establishConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
    }

    private void setUpProperties(String fileName) {
        try {
            PropertiesLoader loader = new PropertiesLoader();
            Properties databaseProperties = loader.loadProperties(fileName);

            this.jdbcUrl = databaseProperties.getProperty(DATABASE_URL);
            this.dbUser = databaseProperties.getProperty(DATABASE_USER);
            this.dbPassword = databaseProperties.getProperty(DATABASE_PASSWORD);

            String driver = databaseProperties.getProperty(DATABASE_DRIVER);
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
