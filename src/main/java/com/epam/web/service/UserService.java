package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private final DaoHelperFactory factory;

    public UserService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public Optional<User> login(String login, String password) {
        Optional<User> user = Optional.empty();
        try (DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            user =  dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public double getCurrentUserBalance(User user) {
        double balance = 0;
        try(DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            balance = dao.getCurrentUserBalance(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public void topUpBalance(double balance, long id) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            dao.topUpBalance(balance, id);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

}
