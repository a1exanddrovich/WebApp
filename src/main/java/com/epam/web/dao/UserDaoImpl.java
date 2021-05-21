package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.UserMapper;
import java.math.BigDecimal;
import java.util.Optional;


public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String TABLE = "user";
    private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = MD5(?)";
    private final static String GET_BALANCE = "SELECT * FROM user WHERE id = ?";
    private final static String TOP_UP_BALANCE = "UPDATE user SET balance = ? WHERE id = ?";

    public UserDaoImpl(ProxyConnection connection) {
        super(connection, new UserMapper(), TABLE);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_USER_BY_LOGIN_AND_PASSWORD, login, password);
    }

    @Override
    public BigDecimal getCurrentUserBalance(long id) throws DaoException {
        Optional<User> optionalUser = executeForSingleResult(GET_BALANCE, id);
        BigDecimal balance = null;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            balance = user.getBalance();
        }
        return balance;
    }

    @Override
    public void topUpBalance(BigDecimal balance, long id) throws DaoException {
        BigDecimal balanceToToppedUp = balance.add(getCurrentUserBalance(id));
        executeUpdate(TOP_UP_BALANCE, balanceToToppedUp, id);
    }

}
