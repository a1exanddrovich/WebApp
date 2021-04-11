package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.OrderMapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao{

    public static final String TABLE = "`order`";
    private final static String GET_ALL_ORDERS = "SELECT * FROM `order`";
    private final static String GET_CURRENT_USER_ORDER = "SELECT * FROM `order` WHERE user_id = ";
    private final static String MAKE_ORDER = "INSERT INTO `order` (user_id, hotel_name, places, class, arrival_date, departure_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_ORDER = "UPDATE `order` SET hotel_name = ?, places = ?, class = ?, arrival_date = ?, departure_date = ?, status = ? WHERE id = ?";
    private final static String DELETE_ORDER = "DELETE FROM `order` WHERE id = ";

    public OrderDaoImpl(ProxyConnection connection) {
        super(connection, new OrderMapper(), TABLE);
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        return executeQuery(GET_ALL_ORDERS);
    }

    @Override
    public void makeOrder(Order order) throws DaoException {
        long userId = order.getUserId();
        String hotelName = order.getHotelName();
        int places = order.getPlaceCount();
        String roomClass = order.getRoomClass();
        Date arrivalDate = order.getArrivalDate();
        Date departureDate = order.getDepartureDate();
        String status = order.getStatus();
        executeUpdate(MAKE_ORDER, userId, hotelName, places, roomClass, arrivalDate, departureDate, status);
    }

    @Override
    public List<Order> getCurrentUserOrders(User user) throws SQLException {
        return executeQuery(GET_CURRENT_USER_ORDER + user.getId());
    }

    @Override
    public void editOrder(Order order) throws DaoException {
        long id = order.getId();
        long userId = order.getUserId();
        String hotelName = order.getHotelName();
        int places = order.getPlaceCount();
        String roomClass = order.getRoomClass();
        Date arrivalDate = order.getArrivalDate();
        Date departureDate = order.getDepartureDate();
        String status = order.getStatus();
        executeUpdate(UPDATE_ORDER, hotelName, places, roomClass, arrivalDate, departureDate, status, id);
    }

    @Override
    public void deleteOrder(long orderId) throws DaoException {
        executeUpdate(DELETE_ORDER + orderId);
    }

}
