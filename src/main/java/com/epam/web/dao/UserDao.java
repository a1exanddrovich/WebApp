package com.epam.web.dao;

import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException, SQLException;

    double getCurrentUserBalance(long id) throws SQLException;

    void topUpBalance(double balance, long id) throws DaoException;

}
