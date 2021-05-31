package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.OrderDao;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.OrderStatus;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private final static String INVALID_ORDER_ID = "Order has not been found. Id is invalid: ";

    private final DaoHelperFactory factory;

    public OrderService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Order> getAllOrders(int currentPage, int recordPerPage) throws ServiceException {
        List<Order> orders = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            List<Order> retrievedOrders = dao.getAllOrders(currentPage, recordPerPage);
            orders.addAll(retrievedOrders);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return orders;
    }

    public void makeOrder(Order order) throws ServiceException {
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.save(order);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Order> getCurrentUserOrders(User user, int currentPage, int recordsPerPage) throws ServiceException {
        List<Order> currentUserOrders = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            List<Order> retrievedOrders = dao.getCurrentUserOrders(user, currentPage, recordsPerPage);
            currentUserOrders.addAll(retrievedOrders);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return currentUserOrders;
    }

    public void editOrder(Order order) throws ServiceException {
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.save(order);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteOrder(long orderId) throws ServiceException {
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            dao.deleteById(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void declineOrderById(long orderId) throws ServiceException {
        try (DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            Optional<Order> optionalOrder = dao.findById(orderId);
            if (optionalOrder.isEmpty()) {
                throw new DaoException(INVALID_ORDER_ID + orderId);
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
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Order> findOrderById(long orderId) throws ServiceException {
        Optional<Order> order = null;
        try (DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            order = dao.findById(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return order;
    }

    public int getOrderCount() throws ServiceException {
        int recordCount = 0;
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            recordCount = dao.countOrders();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return recordCount;
    }

    public int getCurrentUserOrdersCount(long userId) throws ServiceException {
        int recordCount = 0;
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            recordCount = dao.countOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return recordCount;
    }

    public List<Order> getCurrentUserAllOrders(User user) throws ServiceException {
        List<Order> orders = new ArrayList<>();
        try(DaoHelper helper = factory.createDaoHelper()) {
            OrderDao dao = helper.createOrderDao();
            List<Order> retrievedOrders = dao.getCurrentUserAllOrders(user);
            orders.addAll(retrievedOrders);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return orders;
    }
}
