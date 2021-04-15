package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entitiy.Reservation;
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
            user = dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public double getCurrentUserBalance(User user) {
        double balance = 0;
        try (DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            balance = dao.getCurrentUserBalance(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public void topUpBalance(double balance, long id) {
        try (DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            dao.topUpBalance(balance, id);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

    public void withdraw(long userId, long reservationId) {
        try (DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao reservationDao = helper.createReservationDao();
            UserDao userDao = helper.createUserDao();
            HotelDao hotelDao = helper.createHotelDao();
            Reservation reservation = reservationDao.findById(reservationId).get();
            double price = reservation.getPrice();
            long hotelId = reservation.getHotelId();
            helper.startTransaction();
            //CHECK FOR ENOUGH MONEY
            userDao.topUpBalance(-price, userId);
            hotelDao.topUpBalance(price, hotelId);
            reservationDao.markReservationAsPaidById(reservation.getId());
            helper.endTransaction();
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }
}
