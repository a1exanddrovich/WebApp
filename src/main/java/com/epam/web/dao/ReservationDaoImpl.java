package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ReservationMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl extends AbstractDao<Reservation> implements ReservationDao {

    public static final String TABLE = "reservation";
    private final static String CREATE = "INSERT INTO reservation (order_id, hotel_id, room_id, user_id, price, is_paid) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE reservation SET order_id = ?, hotel_id = ?, room_id = ?, user_id = ?, price = ?, is_paid = ? WHERE id = ?";
    private final static String GET_ALL_RESERVATIONS = "SELECT * FROM reservation";
    private final static String GET_CURRENT_USER_RESERVATION = "SELECT * FROM reservation WHERE user_id = ";
    private final static String MAKE_RESERVATION = "INSERT INTO reservation (order_id, hotel_id, room_id, user_id, price) VALUES (?, ?, ?, ?, ?)";
    private final static String FIND_RESERVATION_BY_ID = "SELECT * FROM reservation WHERE id = ?";
    private final static String MARK_AS_PAID = "UPDATE reservation SET is_paid = 1 WHERE id = ?";
    private final static String DELETE_RESERVATION = "DELETE FROM reservation WHERE id = ";
    private final static String GET_COUNT_CURRENT_USER = "SELECT COUNT(*) FROM reservation WHERE user_id = ";
    private final static String GET_CURRENT_USER_RESERVATION_LIMIT = "SELECT * FROM reservation WHERE user_id = ? LIMIT ?, ?";


    public ReservationDaoImpl(ProxyConnection connection) {
        super(connection, new ReservationMapper(), TABLE);
    }

    @Override
    public List<Reservation> getAllReservations() throws DaoException {
        return super.findAll();
    }

    @Override
    public List<Reservation> getCurrentUserReservations(User user, int currentPage, int recordsPerPage) throws DaoException {
        return executeQuery(GET_CURRENT_USER_RESERVATION_LIMIT, user.getId(), (currentPage - 1) * recordsPerPage, recordsPerPage);
    }

    @Override
    protected void create(Reservation reservation) throws DaoException {
        executeUpdate(CREATE, reservation.getOrderId(), reservation.getHotelId(), reservation.getRoomId(), reservation.getUserId(), reservation.getPrice(), reservation.isPaid());
    }

    @Override
    protected void update(Reservation reservation) throws DaoException {
        Optional<Reservation> optionalHotel = findById(reservation.getId());
        if (optionalHotel.isEmpty()) {
            throw new DaoException("Reservation has not been found. Id is invalid: " + reservation.getId());
        }
        executeUpdate(UPDATE, reservation.getOrderId(), reservation.getHotelId(), reservation.getRoomId(), reservation.getUserId(), reservation.getPrice(), reservation.isPaid(), reservation.getId());
    }









//    @Override
//    public void makeReservation(Reservation reservation) throws DaoException {
//        long orderId = reservation.getOrderId();
//        long hotelId = reservation.getHotelId();
//        long roomId = reservation.getRoomId();
//        long userId = reservation.getUserId();
//        BigDecimal price = reservation.getPrice();
//        executeUpdate(MAKE_RESERVATION, orderId, hotelId, roomId, userId, price);
//    }

//    @Override
//    public void markReservationAsPaidById(long id) throws DaoException {
//        executeUpdate(MARK_AS_PAID, id);
//    }













    @Override
    public Optional<Reservation> findById(long reservationId) throws DaoException {
        return super.findById(reservationId);
    }

    @Override
    public void deleteById(long reservationId) throws DaoException {
        super.deleteById(reservationId);
    }

    @Override
    public int countCurrentUserReservations(long userId) throws DaoException {
        return getCount(GET_COUNT_CURRENT_USER + userId);
    }

}
