package com.epam.web.mapper;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.OrderStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderMapper implements Mapper<Order> {

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String HOTEL_NAME = "hotel_name";
    private static final String PLACES = "places";
    private static final String CLASS = "class";
    private static final String ARRIVAL_DATE = "arrival_date";
    private static final String DEPARTURE_DATE = "departure_date";
    private static final String STATUS = "status";

    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        long userId = resultSet.getLong(USER_ID);
        String hotelName = resultSet.getString(HOTEL_NAME);
        int places = resultSet.getInt(PLACES);
        String hotelClass = resultSet.getString(CLASS);
        Date arrivalDate = resultSet.getDate(ARRIVAL_DATE);
        Date departureDate = resultSet.getDate(DEPARTURE_DATE);
        OrderStatus status = OrderStatus.valueOf(resultSet.getString(STATUS));

        Order order = new Order(id, userId, hotelName, hotelClass, places, arrivalDate, departureDate, status);
        return order;
    }
}
