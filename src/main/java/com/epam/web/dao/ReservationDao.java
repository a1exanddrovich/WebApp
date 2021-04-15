package com.epam.web.dao;

import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ReservationDao extends Dao<Reservation>{

    List<Reservation> getAllReservations() throws SQLException;

    List<Reservation> getCurrentUserReservations(User user) throws SQLException;

    void makeReservation(Reservation reservation) throws DaoException;

    Optional<Reservation> findById(long reservationId) throws SQLException;

    void markReservationAsPaidById(long id) throws DaoException;

    void deleteById(long reservationId) throws DaoException;

}
