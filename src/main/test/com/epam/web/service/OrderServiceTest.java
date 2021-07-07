package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.OrderDao;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    private static DaoHelperFactory daoHelperFactory;
    private static DaoHelper daoHelper;
    private static OrderDao orderDao;
    private static Order order;
    private static User user;
    private static OrderService service;

    @BeforeClass
    public static void init() throws DaoException {
        user=Mockito.mock(User.class);
        orderDao = Mockito.mock(OrderDao.class);
        order = Mockito.mock(Order.class);
        daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        daoHelper = Mockito.mock(DaoHelper.class);
        when(daoHelper.createOrderDao()).thenReturn(orderDao);
        when(daoHelperFactory.createDaoHelper()).thenReturn(daoHelper);
        Mockito.doNothing().when(daoHelper).startTransaction();
        Mockito.doNothing().when(daoHelper).endTransaction();
        service = new OrderService(daoHelperFactory);
    }

    @Test
    public void testShouldGetAllOrders() throws DaoException, ServiceException {
        //given
        when(orderDao.getAllOrders(anyInt(), anyInt())).thenReturn(new ArrayList<>());

        //when
        List<Order> orders = service.getAllOrders(1, 1);

        //then
        Assert.assertNotNull(orders);
    }

    @Test
    public void testShouldMakeOrder() throws ServiceException, DaoException {
        //when
        service.makeOrder(order);

        //then
        Mockito.verify(orderDao, Mockito.times(1)).save(order);
    }

    @Test
    public void testShouldGetCurrentUserOrders() throws DaoException, ServiceException {
        //given
        when(orderDao.getCurrentUserOrders(user, anyInt(), anyInt())).thenReturn(new ArrayList<>());

        //when
        List<Order> orders = service.getCurrentUserOrders(user, 1, 1);

        //then
        Assert.assertNotNull(orders);
    }

    @Test
    public void testShouldGetCurrentUserAllOrders() throws DaoException, ServiceException {
        //given
        when(orderDao.getCurrentUserAllOrders(user)).thenReturn(new ArrayList<>());

        //when
        List<Order> orders = service.getCurrentUserAllOrders(user);

        //then
        Assert.assertNotNull(orders);
    }

    @Test
    public void testShouldCountOrders() throws DaoException, ServiceException {
        //given
        when(orderDao.countOrders()).thenReturn(11);

        //when
        int actual = service.getOrderCount();

        //then
        Assert.assertEquals(11, actual);
    }

    @Test
    public void testShouldCountCurrentUserOrders() throws DaoException, ServiceException {
        //given
        when(orderDao.countOrders(anyLong())).thenReturn(9);

        //when
        int actual = service.getCurrentUserOrdersCount(1);

        //then
        Assert.assertEquals(9, actual);
    }

    @Test
    public void testShouldFindById() throws DaoException, ServiceException {
        //given
        when(orderDao.findById(anyLong())).thenReturn(Optional.of(order));

        //when
        Optional<Order> reservation = service.findOrderById(1);

        //then
        Assert.assertTrue(reservation.isPresent());
    }

    @Test
    public void testShouldEditOrder() throws ServiceException, DaoException {
        //when
        service.editOrder(order);

        //then
        Mockito.verify(orderDao, Mockito.times(1)).save(order);
    }

    @Test
    public void testShouldDeleteOrder() throws ServiceException, DaoException {
        //when
        service.deleteOrder(1);

        //then
        Mockito.verify(orderDao, Mockito.times(1)).deleteById(1);
    }

    @Test
    public void testShouldDeclineOrderById() throws ServiceException, DaoException {
        //when
        service.declineOrderById(1);
        when(orderDao.findById(anyLong())).thenReturn(Optional.of(order));

        //then
        Mockito.verify(orderDao, Mockito.times(1)).findById(1);
        Mockito.verify(orderDao, Mockito.times(1)).save(order);
    }

}
