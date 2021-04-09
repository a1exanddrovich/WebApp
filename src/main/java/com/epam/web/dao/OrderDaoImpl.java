package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entitiy.Order;
import com.epam.web.mapper.OrderMapper;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao{

    public static final String TABLE = "`order`";
    private final static String GET_ALL_ORDERS = "SELECT * FROM `order`";

    public OrderDaoImpl(ProxyConnection connection) {
        super(connection, new OrderMapper(), TABLE);
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        return executeQuery(GET_ALL_ORDERS);
    }
}
