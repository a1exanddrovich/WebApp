package com.epam.web.dao;

import com.epam.web.entitiy.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao<Order>{

    List<Order> getAllOrders() throws SQLException;

}
