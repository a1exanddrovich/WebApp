package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entitiy.Hotel;
import com.epam.web.entitiy.Reservation;
import com.epam.web.entitiy.User;
import com.epam.web.entitiy.UserRole;
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
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private final static String MOCK_LOGIN = "login";
    private final static String MOCK_PASSWORD = "password";
    private final static long MOCK_ID = 1L;
    private static DaoHelperFactory daoHelperFactory;
    private static DaoHelper daoHelper;
    private static UserDao userDao;
    private static ReservationDao reservationDao;
    private static HotelDao hotelDao;
    private static OrderDao orderDao;
    private static UserService service;
    private static User user;

    @BeforeClass
    public static void init() throws DaoException {
        reservationDao = Mockito.mock(ReservationDaoImpl.class);
        hotelDao = Mockito.mock(HotelDaoImpl.class);
        userDao = Mockito.mock(UserDaoImpl.class);
        orderDao = Mockito.mock(OrderDaoImpl.class);
        daoHelper = Mockito.mock(DaoHelper.class);
        daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        user = Mockito.mock(User.class);
        when(daoHelperFactory.createDaoHelper()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(daoHelper.createReservationDao()).thenReturn(reservationDao);
        when(daoHelper.createHotelDao()).thenReturn(hotelDao);
        when(daoHelper.createOrderDao()).thenReturn(orderDao);
        service = new UserService(daoHelperFactory);
        Mockito.doNothing().when(daoHelper).startTransaction();
        Mockito.doNothing().when(daoHelper).endTransaction();
    }

    @Test
    public void testShouldLoginUser() throws DaoException, ServiceException {
        //given
        when(userDao.findUserByLoginAndPassword(anyString(), anyString())).thenReturn(Optional.of(user));

        //when
        Optional<User> actual = service.login(MOCK_LOGIN, MOCK_PASSWORD);

        //then
        Assert.assertTrue(actual.isPresent());
    }

    @Test
    public void testShouldGetCurrentUserBalance() throws DaoException, ServiceException {
        //given
        when(userDao.getCurrentUserBalance(anyLong())).thenReturn(new BigDecimal("135.42"));

        //when
        BigDecimal actual = service.getCurrentUserBalance(new User(MOCK_ID, MOCK_LOGIN, MOCK_PASSWORD,
                new BigDecimal("135.42"), UserRole.USER, false));

        //then
        Assert.assertEquals(new BigDecimal("135.42"), actual);
    }

    @Test
    public void testShouldTopUpBalance() throws DaoException, ServiceException {
        //when
        service.topUpBalance(new BigDecimal("45"), MOCK_ID);

        //then
        Mockito.verify(userDao, Mockito.times(1)).topUpBalance(new BigDecimal("45"), MOCK_ID);
    }

    @Test
    public void testShouldReturnTrueWhenPriceLessThanUserBalance() throws DaoException, ServiceException {
        //given
        when(userDao.getCurrentUserBalance(anyLong())).thenReturn(new BigDecimal("4042.42"));
        when(reservationDao.findById(anyLong())).thenReturn(Optional.of(new Reservation(0, 0, 0, 0, 0, new BigDecimal("3562"), false)));

        //when
        boolean actual = service.checkForUserBalance(1, 1);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testShouldReturnFalseWhenUserBalanceLessThanPrice() throws DaoException, ServiceException {
        //given
        when(userDao.getCurrentUserBalance(anyLong())).thenReturn(new BigDecimal("4042.42"));
        when(reservationDao.findById(anyLong())).thenReturn(Optional.of(new Reservation(0, 0, 0, 0, 0, new BigDecimal("356565.8"), false)));

        //when
        boolean actual = service.checkForUserBalance(1, 1);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldCountUsers() throws DaoException, ServiceException {
        //given
        when(userDao.countUsers()).thenReturn(10);

        //when
        int actual = service.getUserCount();

        //then
        Assert.assertEquals(10, actual);
    }

    @Test
    public void testShouldWithdrawMoney() throws DaoException, ServiceException {
        //given
        when(reservationDao.findById(anyLong())).thenReturn(Optional.of(new Reservation(0, 0, 0, 0, 0,new BigDecimal("50"), false)));
        when(hotelDao.findById(anyLong())).thenReturn(Optional.of(new Hotel(0, "name", "description", "imagePath", new BigDecimal(0))));
        when(service.checkForUserBalance(MOCK_ID, 1)).thenReturn(true);

        //when
        service.withdraw(MOCK_ID, 1);

        //then
        Mockito.verify(userDao, Mockito.times(1)).topUpBalance(new BigDecimal("-50"), MOCK_ID);
        Mockito.verify(hotelDao, Mockito.times(1)).save(new Hotel(0, "name", "description", "imagePath", new BigDecimal("50")));
        Mockito.verify(reservationDao, Mockito.times(1)).save(new Reservation(0, 0, 0, 0, MOCK_ID, new BigDecimal("50"), true));
    }

    @Test
    public void testShouldGetAllUsers() throws DaoException, ServiceException {
        //given
        when(userDao.getAllUsers(anyInt(), anyInt())).thenReturn(new ArrayList<>());

        //when
        List<User> users = service.getAllUsers(1, 1);

        //then
        Assert.assertNotNull(users);
    }

    @Test
    public void testShouldBlockUser() throws ServiceException, DaoException {
        //when
        service.blockUser(MOCK_ID, true);

        //then
        Mockito.verify(userDao, Mockito.times(1)).blockUser(MOCK_ID, true);
    }

}
