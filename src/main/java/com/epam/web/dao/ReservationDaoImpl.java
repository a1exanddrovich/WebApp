package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ReservationMapper;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl extends AbstractDao<Reservation> implements ReservationDao {

    public static final String TABLE = "reservation";
    private final static String CREATE = "INSERT INTO reservation (order_id, hotel_id, room_id, user_id, price, is_paid) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE reservation SET order_id = ?, hotel_id = ?, room_id = ?, user_id = ?, price = ?, is_paid = ? WHERE id = ?";
    private final static String GET_COUNT_CURRENT_USER = "SELECT COUNT(*) FROM reservation WHERE user_id = ";
    private final static String GET_CURRENT_USER_RESERVATION_LIMIT = "SELECT * FROM reservation WHERE user_id = ? LIMIT ?, ?";
    private final static String INVALID_RESERVATION_ID = "Reservation has not been found. Id is invalid: ";


    public ReservationDaoImpl(ProxyConnection connection) {
        super(connection, new ReservationMapper(), TABLE);
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
            throw new DaoException(INVALID_RESERVATION_ID + reservation.getId());
        }
        executeUpdate(UPDATE, reservation.getOrderId(), reservation.getHotelId(), reservation.getRoomId(), reservation.getUserId(), reservation.getPrice(), reservation.isPaid(), reservation.getId());
    }

    @Override
    public int countCurrentUserReservations(long userId) throws DaoException {
        return getCount(GET_COUNT_CURRENT_USER + userId);
    }

}
