package com.epam.web.dao;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao<Order>{

    List<Order> getAllOrders() throws SQLException;

    void makeOrder(Order order) throws DaoException;

    List<Order> getCurrentUserOrders(User user) throws SQLException;

    void editOrder(Order order) throws DaoException;

    void deleteOrder(long orderId) throws DaoException;

}
