package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.OrderDao;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void makeOrder(Order order) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.makeOrder(order);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getCurrentUserOrders(User user) {
        List<Order> currentUserOrders = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            List<Order> retrievedOrders = dao.getCurrentUserOrders(user);
            currentUserOrders.addAll(retrievedOrders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUserOrders;
    }

    public void editOrder(Order order) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.editOrder(order);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(long orderId) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.deleteOrder(orderId);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

    public void declineOrderById(long orderId) {
        try (DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.declineOrderById(orderId);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        }
    }

    public Order findOrderById(long orderId) {
        Optional<Order> order = null;
        try (DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            order = dao.findOrderById(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order.get();
    }
}
