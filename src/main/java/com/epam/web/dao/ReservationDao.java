package com.epam.web.dao;

import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ReservationDao extends Dao<Reservation>{

    List<Reservation> getAllReservations() throws SQLException, DaoException;

    List<Reservation> getCurrentUserReservations(User user, int currentPage, int recordsPerPage) throws SQLException, DaoException;

//    void makeReservation(Reservation reservation) throws DaoException;

    Optional<Reservation> findById(long reservationId) throws SQLException, DaoException;

//    void markReservationAsPaidById(long id) throws DaoException;

    void deleteById(long reservationId) throws DaoException;

    int countCurrentUserReservations(long userId) throws SQLException, DaoException;
}
