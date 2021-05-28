package com.epam.web.dao;

import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao<Order>{

    List<Order> getAllOrders(int currentPage, int recordPerPage) throws DaoException;

    List<Order> getCurrentUserOrders(User user, int currentPage, int recordsPerPage) throws DaoException;

    int countOrders() throws DaoException;

    int countOrders(long userId) throws DaoException;

    List<Order> getCurrentUserAllOrders(User user) throws DaoException;

}
