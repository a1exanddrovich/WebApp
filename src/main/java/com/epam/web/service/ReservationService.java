package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.Room;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService {

    private final DaoHelperFactory factory;

    public ReservationService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao dao = helper.createReservationDao();
            List<Reservation> retrievedReservations = dao.getAllReservations();
            reservations.addAll(retrievedReservations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> getCurrentUserReservations(User user) {
        List<Reservation> reservations = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao dao = helper.createReservationDao();
            List<Reservation> retrievedReservations = dao.getCurrentUserReservations(user);
            reservations.addAll(retrievedReservations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public Reservation findById(long id) {
        Optional<Reservation> reservation = null;
        try (DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao dao = helper.createReservationDao();
            reservation = dao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation.get();
    }

    public void makeReservation(Reservation reservation, Order order, Room room) {
        double price = 100;
//        double price = Calculator.calculatePrice(order.getDepartureDate(), order.getArrivalDate());
        reservation.setPrice(price);
        try(DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao reservationDao = helper.createReservationDao();
            RoomDao roomDao = helper.createRoomDao();
            OrderDao orderDao = helper.createOrderDao();
            helper.startTransaction();
            reservationDao.makeReservation(reservation);
            roomDao.updateRoom(room, order.getArrivalDate(), order.getDepartureDate());
            orderDao.updateOrder(order);
            helper.endTransaction();
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

    public void refuseReservation(Reservation reservation) {
        long reservationId = reservation.getId();
        long orderId = reservation.getOrderId();
        long roomId = reservation.getRoomId();
        try (DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao reservationDao = helper.createReservationDao();
            OrderDao orderDao = helper.createOrderDao();
            RoomDao roomDao = helper.createRoomDao();
            helper.startTransaction();
            roomDao.unbookRoomById(roomId);
            reservationDao.deleteById(reservationId);
            orderDao.deleteOrder(orderId);
            helper.endTransaction();
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }
}
