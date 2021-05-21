package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.OrderDao;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.OrderStatus;
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

    public List<Order> getAllOrders(int currentPage, int recordPerPage) {
        List<Order> orders = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            List<Order> retrievedOrders = dao.getAllOrders(currentPage, recordPerPage);
            orders.addAll(retrievedOrders);
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return orders;
    }

    public void makeOrder(Order order) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.save(order);
        } catch (DaoException e) {
            throw new SecurityException(e);
        }
    }

    public List<Order> getCurrentUserOrders(User user, int currentPage, int recordsPerPage) {
        List<Order> currentUserOrders = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            List<Order> retrievedOrders = dao.getCurrentUserOrders(user, currentPage, recordsPerPage);
            currentUserOrders.addAll(retrievedOrders);
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return currentUserOrders;
    }

    public void editOrder(Order order) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.save(order);
        } catch (DaoException e) {
            throw new SecurityException(e);
        }
    }

    public void deleteOrder(long orderId) {
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.deleteOrder(orderId);
        } catch (DaoException e) {
            throw new SecurityException(e);
        }
    }

    public void declineOrderById(long orderId) {
        try (DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            Optional<Order> optionalOrder = dao.findOrderById(orderId);
            if (optionalOrder.isEmpty()) {
                throw new DaoException("Order has not been found. Id is invalid: " + orderId);
            }
            Order order = optionalOrder.get();
            dao.save(new Order(order.getId(),
                         order.getUserId(),
                         order.getHotelName(),
                         order.getRoomClass(),
                         order.getPlaceCount(),
                         order.getArrivalDate(),
                         order.getDepartureDate(),
                         OrderStatus.DECLINED));
        } catch (DaoException | SQLException e) {
            throw new SecurityException(e);
        }
    }

    public Order findOrderById(long orderId) {
        Optional<Order> order = null;
        try (DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            order = dao.findOrderById(orderId);
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return order.get();
    }

    public int getOrderCount() {
        int recordCount = 0;
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            recordCount = dao.countOrders();
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return recordCount;
    }

    public int getCurrentUserOrdersCount(long userId) {
        int recordCount = 0;
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            recordCount = dao.countOrders(userId);
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return recordCount;
    }

    public List<Order> getCurrentUserAllOrders(User user) {
        List<Order> orders = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            List<Order> retrievedOrders = dao.getCurrentUserAllOrders(user);
            orders.addAll(retrievedOrders);
        } catch (SQLException | DaoException e) {
            throw new SecurityException(e);
        }
        return orders;
    }
}
