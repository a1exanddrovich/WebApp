package com.epam.web.service;

import com.epam.web.calculator.ReservationPriceCalculator;
import com.epam.web.dao.*;
import com.epam.web.entitiy.*;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService {

    private final DaoHelperFactory factory;
    private final ReservationPriceCalculator calculator = new ReservationPriceCalculator();

    public ReservationService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Reservation> getCurrentUserReservations(User user, int currentPage, int recordsPerPage) throws ServiceException {
        List<Reservation> reservations = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao dao = helper.createReservationDao();
            List<Reservation> retrievedReservations = dao.getCurrentUserReservations(user, currentPage, recordsPerPage);
            reservations.addAll(retrievedReservations);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return reservations;
    }

    public Reservation findById(long id) throws ServiceException {
        Optional<Reservation> reservation = null;
        try (DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao dao = helper.createReservationDao();
            reservation = dao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return reservation.get();
    }

    public void makeReservation(Reservation reservation, Order order, Room room) throws ServiceException {
        BigDecimal price = calculator.calculatePrice(order.getArrivalDate(), order.getDepartureDate(), room.getRoomClass().toString());
        Reservation newReservation = new Reservation(reservation.getId(),
                                                     reservation.getOrderId(),
                                                     reservation.getHotelId(),
                                                     reservation.getRoomId(),
                                                     reservation.getUserId(),
                                                     price,
                                                     false);
        try(DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao reservationDao = helper.createReservationDao();
            RoomDao roomDao = helper.createRoomDao();
            OrderDao orderDao = helper.createOrderDao();
            helper.startTransaction();
            reservationDao.save(newReservation);
            Room updatedRoom = new Room(room.getId(),
                                        room.getHotelId(),
                                        room.getRoomClass(),
                                        room.getPlaceCount(),
                                        order.getArrivalDate(),
                                        order.getDepartureDate());
            roomDao.save(updatedRoom);
            Order updatedOrder = new Order(order.getId(),
                                           order.getUserId(),
                                           order.getHotelName(),
                                           order.getRoomClass(),
                                           order.getPlaceCount(),
                                           order.getArrivalDate(),
                                           order.getDepartureDate(),
                                           OrderStatus.ACCEPTED);
            orderDao.save(updatedOrder);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void refuseReservation(Reservation reservation) throws ServiceException {
        long reservationId = reservation.getId();
        long orderId = reservation.getOrderId();
        long roomId = reservation.getRoomId();
        try (DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao reservationDao = helper.createReservationDao();
            OrderDao orderDao = helper.createOrderDao();
            RoomDao roomDao = helper.createRoomDao();
            helper.startTransaction();
            Optional<Room> optionalRoom = roomDao.findById(roomId);
            if (optionalRoom.isEmpty()) {
                throw new DaoException("Room has not been found. Id is invalid: " + roomId);
            }
            Room room = optionalRoom.get();
            Room updatedRoom = new Room(roomId, room.getHotelId(), room.getRoomClass(), room.getPlaceCount(), null, null);
            roomDao.save(updatedRoom);
            reservationDao.deleteById(reservationId);
            orderDao.deleteById(orderId);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int getReservationCount(long userId) throws ServiceException {
        int recordCount = 0;
        try(DaoHelper helper = factory.createDaoHelper()) {
            ReservationDao dao = helper.createReservationDao();
            recordCount = dao.countCurrentUserReservations(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return recordCount;
    }
}
