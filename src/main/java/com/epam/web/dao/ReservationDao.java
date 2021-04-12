package com.epam.web.dao;

import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDao extends Dao<Reservation>{

    List<Reservation> getAllReservations() throws SQLException;

    List<Reservation> getCurrentUserReservations(User user) throws SQLException;

}
