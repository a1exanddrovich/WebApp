package com.epam.web.dao;

import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    BigDecimal getCurrentUserBalance(long id) throws DaoException;

    List<User> getAllUsers(int currentPage, int recordPerPage) throws DaoException;

    int countUsers() throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

}
