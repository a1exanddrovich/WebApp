package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ReservationMapper;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl extends AbstractDao<Reservation> implements ReservationDao {

    public static final String TABLE = "reservation";
    private final static String GET_ALL_RESERVATIONS = "SELECT * FROM reservation";
    private final static String GET_CURRENT_USER_RESERVATION = "SELECT * FROM reservation WHERE user_id = ";
    private final static String MAKE_RESERVATION = "INSERT INTO reservation (order_id, hotel_id, room_id, user_id, price) VALUES (?, ?, ?, ?, ?)";
    private final static String FIND_RESERVATION_BY_ID = "SELECT * FROM reservation WHERE id = ?";
    private final static String MARK_AS_PAID = "UPDATE reservation SET is_paid = 1 WHERE id = ?";
    private final static String DELETE_RESERVATION = "DELETE FROM reservation WHERE id = ";

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

    @Override
    public void makeReservation(Reservation reservation) throws DaoException {
        long orderId = reservation.getOrderId();
        long hotelId = reservation.getHotelId();
        long roomId = reservation.getRoomId();
        long userId = reservation.getUserId();
        double price = reservation.getPrice();
        executeUpdate(MAKE_RESERVATION, orderId, hotelId, roomId, userId, price);
    }

    @Override
    public Optional<Reservation> findById(long reservationId) throws SQLException {
        return executeForSingleResult(FIND_RESERVATION_BY_ID, reservationId);
    }

    @Override
    public void markReservationAsPaidById(long id) throws DaoException {
        executeUpdate(MARK_AS_PAID, id);
    }

    @Override
    public void deleteById(long reservationId) throws DaoException {
        executeUpdate(DELETE_RESERVATION + reservationId);
    }

}
