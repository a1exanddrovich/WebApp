package com.epam.web.dao;

import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException, SQLException;

    BigDecimal getCurrentUserBalance(long id) throws SQLException, DaoException;

    void topUpBalance(BigDecimal balance, long id) throws DaoException;

}
