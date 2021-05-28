package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.UserMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private final static String TABLE = "user";
    private final static String UPDATE = "UPDATE user SET login = ?, password = MD5(?), role = ?, balance = ? WHERE id = ?";
    private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = MD5(?)";
    private final static String GET_BALANCE = "SELECT * FROM user WHERE id = ?";
    private final static String TOP_UP_BALANCE = "UPDATE user SET balance = ? WHERE id = ?";
    private final static String BLOCK_USER = "UPDATE user SET is_blocked = ? WHERE id = ?";
    private final static String GET_ALL_USERS = "SELECT * FROM user LIMIT ?, ?";
    private final static String GET_COUNT = "SELECT COUNT(*) FROM user";

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

//    @Override
//    protected void update(User user) throws DaoException {
//        Optional<User> optionalUser = findById(user.getId());
//        if (optionalUser.isEmpty()) {
//            throw new DaoException("User has not been found. Id is invalid: " + user.getId());
//        }
//        executeUpdate(UPDATE, user.getLogin(), user.getPassword(), user.getRole(), user.getBalance(), user.getId());
//    }

    @Override
    public List<User> getAllUsers(int currentPage, int recordPerPage) throws DaoException {
        return executeQuery(GET_ALL_USERS, (currentPage - 1) * recordPerPage, recordPerPage);
    }

    @Override
    public int countUsers() throws DaoException {
        return getCount(GET_COUNT);
    }

    @Override
    public void blockUser(long userId, boolean block) throws DaoException {
        executeUpdate(BLOCK_USER, block, userId);
    }

}
