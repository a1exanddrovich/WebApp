package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.UserMapper;
import java.sql.*;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String TABLE = "user";
    private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ?";
    private final static String GET_BALANCE = "SELECT * FROM user WHERE id = ?";
    private final static String TOP_UP_BALANCE = "UPDATE user SET balance = ? WHERE id = ?";

    public UserDaoImpl(ProxyConnection connection) {
        super(connection, new UserMapper(), TABLE);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws SQLException {
        return executeForSingleResult(FIND_USER_BY_LOGIN_AND_PASSWORD, login, password);
    }

    @Override
    public double getCurrentUserBalance(long id) throws SQLException {
        Optional<User> optionalUser = executeForSingleResult(GET_BALANCE, id);
        User user = optionalUser.get();
        return user.getBalance();
    }

    @Override
    public void topUpBalance(double balance, long id) throws DaoException {
        try {
            balance += getCurrentUserBalance(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executeUpdate(TOP_UP_BALANCE, balance, id);
    }

    //get by id, save, remove

}
