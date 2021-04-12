package com.epam.web.mapper;

import com.epam.web.entitiy.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements Mapper<Reservation> {

    private static final String ID = "id";
    private static final String ORDER_ID = "order_id";
    private static final String HOTEL_ID = "hotel_id";
    private static final String ROOM_ID = "room_id";
    private static final String USER_ID = "user_id";
    private static final String PRICE = "price";
    private static final String IS_PAID = "is_paid";

    @Override
    public Reservation map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        long orderId = resultSet.getLong(ORDER_ID);
        long hotelId = resultSet.getLong(HOTEL_ID);
        long roomId = resultSet.getLong(ROOM_ID);
        long userId = resultSet.getLong(USER_ID);
        long price = resultSet.getLong(PRICE);
        boolean isPaid = resultSet.getBoolean(IS_PAID);
        Reservation reservation = new Reservation(id, orderId, hotelId, roomId, userId, price, isPaid);
        return reservation;
    }

}
