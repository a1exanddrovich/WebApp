package com.epam.web.connection;

import com.epam.web.exception.ConnectionPoolException;
import com.epam.web.utils.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPoolFactory {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPoolFactory.class);
    private final static String PROPERTIES_FILENAME = "database/database.properties";
    private final static String DATABASE_URL = "database.url";
    private final static String DATABASE_USER = "database.user";
    private final static String DATABASE_PASSWORD = "database.password";
    private final static String DATABASE_POOL_SIZE = "database.pool_size";
    private final static String DATABASE_DRIVER = "database.driver";

    private String jdbcUrl;
    private String dbUser;
    private String dbPassword;
    private int poolSize;


    private void setUpProperties(String fileName) throws ClassNotFoundException, IOException {
        PropertiesLoader loader = new PropertiesLoader();
        Properties databaseProperties = loader.loadProperties(fileName);
        this.jdbcUrl = databaseProperties.getProperty(DATABASE_URL);
        this.dbUser = databaseProperties.getProperty(DATABASE_USER);
        this.dbPassword = databaseProperties.getProperty(DATABASE_PASSWORD);
        this.poolSize = Integer.parseInt(databaseProperties.getProperty(DATABASE_POOL_SIZE));
        String driver = databaseProperties.getProperty(DATABASE_DRIVER);
        Class.forName(driver);
    }

    private List<ProxyConnection> establishConnections() throws SQLException {
        List<ProxyConnection> connections = new ArrayList<>();
        for (int i = 0; i < this.poolSize; i++) {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            connections.add(proxyConnection);
        }
        return connections;
    }

    public ConnectionPool createConnectionPool() {
        try {
            setUpProperties(PROPERTIES_FILENAME);
            List<ProxyConnection> connections = establishConnections();
            return new ConnectionPool(this.poolSize, connections);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            LOGGER.fatal(e.getMessage());
            e.printStackTrace();
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

}
