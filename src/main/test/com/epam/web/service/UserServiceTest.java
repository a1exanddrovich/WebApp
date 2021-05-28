package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.UserDaoImpl;
import com.epam.web.entitiy.User;
import com.epam.web.exception.DaoException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private final static long id = 1L;
    private static DaoHelperFactory daoHelperFactory;
    private static DaoHelper daoHelper;
    private static UserDao userDao;
    private static UserService service;
    private static User user;

    @BeforeClass
    public static void init() {
        userDao = Mockito.mock(UserDaoImpl.class);
        daoHelper = Mockito.mock(DaoHelper.class);
        daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        user = Mockito.mock(User.class);
        when(daoHelperFactory.createDaoHelper()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        service = new UserService(daoHelperFactory);
    }

    @Test
    public void testShouldFindById() throws DaoException {
        //given
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));

        //when
        //Optional<User> actual = Optional.of(service.)
    }

}
