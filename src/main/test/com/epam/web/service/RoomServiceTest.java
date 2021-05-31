package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.RoomDao;
import com.epam.web.dao.RoomDaoImpl;
import com.epam.web.entitiy.Room;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class RoomServiceTest {

    private static DaoHelperFactory daoHelperFactory;
    private static DaoHelper daoHelper;
    private static RoomDao roomDao;
    private static Room room;
    private static RoomService service;

    @BeforeClass
    public static void init() throws DaoException {
        daoHelper = Mockito.mock(DaoHelper.class);
        daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        roomDao = Mockito.mock(RoomDaoImpl.class);
        room = Mockito.mock(Room.class);
        when(daoHelperFactory.createDaoHelper()).thenReturn(daoHelper);
        when(daoHelper.createRoomDao()).thenReturn(roomDao);
        service = new RoomService(daoHelperFactory);
        Mockito.doNothing().when(daoHelper).startTransaction();
        Mockito.doNothing().when(daoHelper).endTransaction();
    }

    @Test
    public void testShouldAddRoom() throws ServiceException, DaoException {
        //when
        service.addRoom(room);

        //then
        Mockito.verify(roomDao, Mockito.times(1)).save(room);
    }

    @Test
    public void testShouldFindById() throws DaoException, ServiceException {
        //given
        when(roomDao.findById(anyLong())).thenReturn(Optional.of(room));

        //when
        Optional<Room> room = service.findRoomById(1);

        //then
        Assert.assertTrue(room.isPresent());
    }

    @Test
    public void testShouldFindRooms() throws ServiceException, DaoException {
        //given
        when(roomDao.findProperRoom(room)).thenReturn(new ArrayList<>());

        //when
        List<Room> hotels = service.findRoom(room);

        //then
        Assert.assertNotNull(hotels);
    }

}
