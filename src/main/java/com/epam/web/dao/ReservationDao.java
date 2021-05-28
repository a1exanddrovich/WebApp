package com.epam.web.dao;

import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.List;

public interface ReservationDao extends Dao<Reservation>{

    List<Reservation> getCurrentUserReservations(User user, int currentPage, int recordsPerPage) throws DaoException;

    int countCurrentUserReservations(long userId) throws DaoException;

}
