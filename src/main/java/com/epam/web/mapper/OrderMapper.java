package com.epam.web.mapper;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements Mapper<Order> {

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String HOTEL_NAME = "hotel_name";
    public static final String PLACES = "places";
    public static final String CLASS = "class";
    public static final String ARRIVAL_DATE = "arrival_date";
    public static final String DEPARTURE_DATE = "departure_date";
    public static final String STATUS = "status";

    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        long userId = resultSet.getLong(USER_ID);
        String hotelName = resultSet.getString(HOTEL_NAME);
        int places = resultSet.getInt(PLACES);
        String hotelClass = resultSet.getString(CLASS);
        String arrivalDate = resultSet.getString(ARRIVAL_DATE);
        String departureDate = resultSet.getString(DEPARTURE_DATE);
        OrderStatus status = OrderStatus.valueOf(resultSet.getString(STATUS));
        Order order = new Order(id, userId, hotelName, hotelClass, places, arrivalDate, departureDate, status);
        return order;
    }
}
