package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entitiy.Hotel;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final DaoHelperFactory factory;

    public UserService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> user = Optional.empty();
        try (DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            user = dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return user;
    }

    public BigDecimal getCurrentUserBalance(User user) throws ServiceException {
        BigDecimal balance = null;
        try (DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            balance = dao.getCurrentUserBalance(user.getId());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return balance;
    }

    public void topUpBalance(BigDecimal balance, long id) throws ServiceException {
        try (DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            dao.topUpBalance(balance, id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean withdraw(long userId, long reservationId) throws ServiceException {
        boolean userAbleToPay = checkForUserBalance(userId, reservationId);
        if (userAbleToPay) {
            try (DaoHelper helper = factory.createDaoHelper()) {
                ReservationDao reservationDao = helper.createReservationDao();
                UserDao userDao = helper.createUserDao();
                HotelDao hotelDao = helper.createHotelDao();
                Optional<Reservation> optionalReservation = reservationDao.findById(reservationId);
                if (optionalReservation.isEmpty()) {
                    throw new DaoException("Reservation has not been found. Id is invalid: " + reservationId);
                }
                Reservation reservation = optionalReservation.get();
                BigDecimal price = reservation.getPrice();
                long hotelId = reservation.getHotelId();
                helper.startTransaction();
                userDao.topUpBalance(price.negate(), userId);
                Optional<Hotel> optionalHotel = hotelDao.findById(hotelId);
                if (optionalHotel.isEmpty()) {
                    throw new DaoException("Hotel has not been found. Id is invalid: " + hotelId);
                }
                Hotel hotel = optionalHotel.get();
                BigDecimal hotelBalance = hotel.getBalance();
                BigDecimal newHotelBalance = price.add(hotelBalance);
                Hotel hotelUpdated = new Hotel(hotel.getId(), hotel.getName(), hotel.getDescription(), hotel.getImagePath(), newHotelBalance);
                hotelDao.save(hotelUpdated);
                Reservation updatedReservation = new Reservation(reservationId, reservation.getOrderId(), reservation.getHotelId(), reservation.getRoomId(), reservation.getUserId(), reservation.getPrice(), true);
                reservationDao.save(updatedReservation);
                helper.endTransaction();
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        return userAbleToPay;
    }

    public boolean checkForUserBalance(long userId, long reservationId) throws ServiceException {
        BigDecimal userBalance = null;
        BigDecimal reservationPrice = null;
        try (DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao reservationDao = helper.createReservationDao();
            UserDao userDao = helper.createUserDao();
            userBalance = userDao.getCurrentUserBalance(userId);
            Reservation reservation = reservationDao.findById(reservationId).get();
            reservationPrice = reservation.getPrice();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return userBalance.compareTo(reservationPrice) >= 1;
    }

    public List<User> getAllUsers(int currentPage, int recordPerPage) throws ServiceException {
        List<User> users = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            List<User> retrievedUsers = dao.getAllUsers(currentPage, recordPerPage);
            users.addAll(retrievedUsers);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return users;
    }

    public int getUserCount() throws ServiceException {
        int recordCount = 0;
        try(DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            recordCount = dao.countUsers();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return recordCount;
    }

    public void blockUser(long userId, boolean block) throws ServiceException {
        try (DaoHelper helper = factory.createDaoHelper()) {
            UserDao dao = helper.createUserDao();
            dao.blockUser(userId, block);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
