package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.User;
import com.epam.web.mapper.UserMapper;
import java.sql.*;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String TABLE = "user";
    private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ?";

    public UserDaoImpl(ProxyConnection connection) {
        super(connection, new UserMapper(), TABLE);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws SQLException {
        return executeForSingleResult(FIND_USER_BY_LOGIN_AND_PASSWORD, login, password);
    }

    //get by id, save, remove

}
