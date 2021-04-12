package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.mapper.ReservationMapper;
import java.sql.SQLException;
import java.util.List;

public class ReservationDaoImpl extends AbstractDao<Reservation> implements ReservationDao {

    public static final String TABLE = "reservation";
    private final static String GET_ALL_RESERVATIONS = "SELECT * FROM reservation";
    private final static String GET_CURRENT_USER_RESERVATION = "SELECT * FROM reservation WHERE user_id = ";

    public ReservationDaoImpl(ProxyConnection connection) {
        super(connection, new ReservationMapper(), TABLE);
    }

    @Override
    public List<Reservation> getAllReservations() throws SQLException {
        return executeQuery(GET_ALL_RESERVATIONS);
    }

    @Override
    public List<Reservation> getCurrentUserReservations(User user) throws SQLException {
        return executeQuery(GET_CURRENT_USER_RESERVATION + user.getId());
    }

}
