package com.epam.web.dao;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends Dao<Order>{

    List<Order> getAllOrders(int currentPage, int recordPerPage) throws SQLException, DaoException;

//    void makeOrder(Order order) throws DaoException;

    List<Order> getCurrentUserOrders(User user, int currentPage, int recordsPerPage) throws SQLException, DaoException;

//    void editOrder(Order order) throws DaoException;

    void deleteOrder(long orderId) throws DaoException;

//    void declineOrderById(long orderId) throws DaoException;

    Optional<Order> findOrderById(long orderId) throws SQLException, DaoException;

//    void updateOrder(Order order) throws DaoException;

    int countOrders() throws SQLException, DaoException;

    int countOrders(long userId) throws SQLException, DaoException;

    List<Order> getCurrentUserAllOrders(User user) throws SQLException, DaoException;

}
