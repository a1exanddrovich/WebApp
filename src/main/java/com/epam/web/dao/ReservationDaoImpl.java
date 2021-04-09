package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Reservation;
import com.epam.web.mapper.ReservationMapper;
import java.sql.SQLException;
import java.util.List;

public class ReservationDaoImpl extends AbstractDao<Reservation> implements ReservationDao {

    public static final String TABLE = "reservation";
    private final static String GET_ALL_RESERVATIONS = "SELECT * FROM reservation";

    public ReservationDaoImpl(ProxyConnection connection) {
        super(connection, new ReservationMapper(), TABLE);
    }

    @Override
    public List<Reservation> getAllReservations() throws SQLException {
        return executeQuery(GET_ALL_RESERVATIONS);
    }
}
