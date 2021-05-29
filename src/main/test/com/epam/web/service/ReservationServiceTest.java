package com.epam.web.service;

import com.epam.web.calculator.ReservationPriceCalculator;
import com.epam.web.dao.*;
import com.epam.web.entitiy.Order;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.Room;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class ReservationServiceTest {

    private static DaoHelperFactory daoHelperFactory;
    private static DaoHelper daoHelper;
    private static ReservationDao reservationDao;
    private static OrderDao orderDao;
    private static RoomDao roomDao;
    private static Reservation reservation;
    private static ReservationService service;
    private static User user;
    private static Order order;
    private static Room room;
    private static ReservationPriceCalculator calculator;

    @BeforeClass
    public static void init() throws DaoException {
        calculator = Mockito.mock(ReservationPriceCalculator.class);
        daoHelper = Mockito.mock(DaoHelper.class);
        daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        reservationDao = Mockito.mock(ReservationDaoImpl.class);
        roomDao = Mockito.mock(RoomDaoImpl.class);
        orderDao = Mockito.mock(OrderDaoImpl.class);
        reservation = Mockito.mock(Reservation.class);
        user = Mockito.mock(User.class);
        room = Mockito.mock(Room.class);
        order = Mockito.mock(Order.class);
        when(daoHelperFactory.createDaoHelper()).thenReturn(daoHelper);
        when(daoHelper.createReservationDao()).thenReturn(reservationDao);
        when(daoHelper.createOrderDao()).thenReturn(orderDao);
        when(daoHelper.createRoomDao()).thenReturn(roomDao);
        service = new ReservationService(daoHelperFactory);
        Mockito.doNothing().when(daoHelper).startTransaction();
        Mockito.doNothing().when(daoHelper).endTransaction();
    }

    @Test
    public void testShouldGetCurrentUserReservations() throws DaoException, ServiceException {
        //given
        when(reservationDao.getCurrentUserReservations(user, anyInt(), anyInt())).thenReturn(new ArrayList<>());

        //when
        List<Reservation> reservations = service.getCurrentUserReservations(user, 1, 1);

        //then
        Assert.assertNotNull(reservations);
    }

    @Test
    public void testShouldFindById() throws DaoException, ServiceException {
        //given
        when(reservationDao.findById(anyLong())).thenReturn(Optional.of(reservation));

        //when
        Optional<Reservation> reservation = Optional.of(service.findById(1));

        //then
        Assert.assertTrue(reservation.isPresent());
    }

    @Test
    public void testShouldCountReservations() throws DaoException, ServiceException {
        //given
        when(reservationDao.countCurrentUserReservations(anyLong())).thenReturn(11);

        //when
        int actual = service.getReservationCount(10);

        //then
        Assert.assertEquals(11, actual);
    }

    @Test
    public void testShouldMakeReservation() throws DaoException, ServiceException {
        //when
        service.makeReservation(reservation, order, room);
        Mockito.doNothing().when(orderDao).save(order);
        Mockito.doNothing().when(roomDao).save(room);
        when(calculator.calculatePrice(any(), any(), any())).thenReturn(new BigDecimal("49658"));

        //then
        Mockito.verify(reservationDao, Mockito.times(1)).save(reservation);
    }

    @Test
    public void testShouldRefuseReservation() throws DaoException, ServiceException {
        //when
        service.makeReservation(reservation, order, room);
        Mockito.doNothing().when(orderDao).save(order);
        Mockito.doNothing().when(roomDao).deleteById(anyLong());

        //then
        Mockito.verify(reservationDao, Mockito.times(1)).save(reservation);
    }

}
