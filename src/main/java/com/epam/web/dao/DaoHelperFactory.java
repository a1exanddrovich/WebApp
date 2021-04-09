package com.epam.web.dao;

import com.epam.web.connection.ConnectionPool;

public class DaoHelperFactory {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public DaoHelper createDaoHelper() {
        return new DaoHelper(connectionPool.getConnection());
    }

}
