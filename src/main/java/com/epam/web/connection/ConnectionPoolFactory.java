package com.epam.web.connection;

import com.epam.web.exception.ConnectionPoolException;
import com.epam.web.utils.PropertiesLoader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPoolFactory {

    private static final String PROPERTIES_FILENAME = "database/database.properties";

    private String jdbcUrl;
    private String dbUser;
    private String dbPassword;
    private int poolSize;


    private void setUpProperties(String fileName) throws ClassNotFoundException, IOException {
        PropertiesLoader loader = new PropertiesLoader();
        Properties databaseProperties = loader.loadProperties(fileName);
        this.jdbcUrl = databaseProperties.getProperty("database.url");
        this.dbUser = databaseProperties.getProperty("database.user");
        this.dbPassword = databaseProperties.getProperty("database.password");
        this.poolSize = Integer.parseInt(databaseProperties.getProperty("database.pool_size"));
        String driver = databaseProperties.getProperty("database.driver");
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
            e.printStackTrace();
            throw new ConnectionPoolException();
        }
    }

}
