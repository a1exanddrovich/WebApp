package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.OrderMapper;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao{

    private final static String TABLE = "`order`";
    private final static String UPDATE = "UPDATE `order` SET user_id = ?, hotel_name = ?, places = ?, class = ?, arrival_date = ?, departure_date = ?, status = ? WHERE id = ?";
    private final static String GET_ALL_ORDERS = "SELECT * FROM `order` WHERE status = 'PROCESSING' LIMIT ?, ?";
    private final static String GET_CURRENT_USER_ALL_ORDER = "SELECT * FROM `order` WHERE status = 'ACCEPTED' AND user_id = ?";
    private final static String GET_CURRENT_USER_ORDER = "SELECT * FROM `order`  WHERE user_id = ? LIMIT ?, ?";
    private final static String CREATE = "INSERT INTO `order` (user_id, hotel_name, places, class, arrival_date, departure_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String GET_COUNT = "SELECT COUNT(*) FROM `order` WHERE status = 'PROCESSING'";
    private final static String GET_COUNT_CURRENT_USER = "SELECT COUNT(*) FROM `order` WHERE user_id = ";


    public OrderDaoImpl(ProxyConnection connection) {
        super(connection, new OrderMapper(), TABLE);
    }

    @Override
    public List<Order> getAllOrders(int currentPage, int recordPerPage) throws DaoException {
        return executeQuery(GET_ALL_ORDERS, (currentPage - 1) * recordPerPage, recordPerPage);
    }

    @Override
    protected void create(Order order) throws DaoException {
        executeUpdate(CREATE, order.getUserId(), order.getHotelName(), order.getPlaceCount(), order.getRoomClass(), order.getArrivalDate(), order.getDepartureDate(), order.getStatus());
    }

    @Override
    protected void update(Order order) throws DaoException {
        Optional<Order> optionalHotel = findById(order.getId());
        if (optionalHotel.isEmpty()) {
            throw new DaoException("Order has not been found. Id is invalid: " + order.getId());
        }
        executeUpdate(UPDATE, order.getUserId(), order.getHotelName(), order.getPlaceCount(), order.getRoomClass(), order.getArrivalDate(), order.getDepartureDate(), order.getStatus(), order.getId());
    }

    @Override
    public List<Order> getCurrentUserOrders(User user, int currentPage, int recordsPerPage) throws DaoException {
        return executeQuery(GET_CURRENT_USER_ORDER, user.getId(), (currentPage - 1) * recordsPerPage, recordsPerPage);
    }

    @Override
    public int countOrders() throws DaoException {
        return getCount(GET_COUNT);
    }

    @Override
    public int countOrders(long userId) throws DaoException {
        return getCount(GET_COUNT_CURRENT_USER + userId);
    }

    @Override
    public List<Order> getCurrentUserAllOrders(User user) throws DaoException {
        return executeQuery(GET_CURRENT_USER_ALL_ORDER, user.getId());
    }

}
