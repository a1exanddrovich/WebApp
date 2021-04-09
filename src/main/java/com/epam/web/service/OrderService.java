package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.OrderDao;
import com.epam.web.entitiy.Order;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final DaoHelperFactory factory;

    public OrderService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            List<Order> retrievedOrders = dao.getAllOrders();
            orders.addAll(retrievedOrders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
